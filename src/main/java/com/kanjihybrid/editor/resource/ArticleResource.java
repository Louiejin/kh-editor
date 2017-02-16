package com.kanjihybrid.editor.resource;

import com.kanjihybrid.editor.dto.ArticleDto;
import com.kanjihybrid.editor.dto.PageDto;
import com.kanjihybrid.editor.mapper.ArticleMapper;
import com.kanjihybrid.editor.model.Article;
import com.kanjihybrid.editor.model.QArticle;
import com.kanjihybrid.editor.repository.ArticleRepo;
import com.kanjihybrid.editor.util.UserUtil;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.kanjihybrid.editor.model.lookup.Role.ROLE_ADMIN;
import static com.kanjihybrid.editor.util.UserUtil.currentUserHasRole;
import static com.kanjihybrid.editor.util.UserUtil.getCurrentUser;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Frank Lloyd Teh
 */
@Transactional
@RestController
@RequestMapping("/article")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class ArticleResource {

    @Resource(name = "articleRepo")
    private ArticleRepo articleRepo;

    @Resource(name = "articleMapper")
    private ArticleMapper articleMapper;

    @RequestMapping(method = GET)
    public PageDto<ArticleDto> query(@RequestParam MultiValueMap<String, String> params, Pageable pageable) {
        Page<Article> page = articleRepo.findAll(constructPredicate(params), pageable);
        List<ArticleDto> content = page.getContent().stream().map(articleMapper::map).collect(toList());
        return new PageDto<>(page, content);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ArticleDto get(@PathVariable Long id) {
        return articleMapper.map(articleRepo.findOne(id));
    }

    @RequestMapping(method = POST)
    public ResponseEntity save(@RequestBody ArticleDto dto) {
        Article article = dto.getId() != null ? articleRepo.findOne(dto.getId()) : new Article();
        articleMapper.map(dto, article);

        if (dto.getId() == null) {
            article.setTranslatedBody(article.getBody());
            article.setTranslatedTitle(article.getTitle());
        }

        articleRepo.save(article);
        return new ResponseEntity(OK);
    }

    @RequestMapping(value = "/claim/{id}", method = GET)
    public ResponseEntity claim(@PathVariable Long id) {
        Article article = articleRepo.findOne(id);
        article.setEditor(UserUtil.getCurrentUser());
        articleRepo.save(article);
        return new ResponseEntity(OK);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity remove(@PathVariable Long id) {
        articleRepo.delete(id);
        return new ResponseEntity(OK);
    }

    private Predicate constructPredicate(MultiValueMap<String, String> params) {

        BooleanBuilder where = new BooleanBuilder();

        if (isNotEmpty(params.getFirst("claimed"))) {
            if (params.getFirst("claimed").equalsIgnoreCase("unclaimed"))
                where.and(QArticle.article.editor.isNull());
            if (params.getFirst("claimed").equalsIgnoreCase("claimed"))
                where.and(QArticle.article.editor.isNotNull());
        } else if (currentUserHasRole(ROLE_ADMIN) && isNotEmpty(params.getFirst("claimedBy"))) {
            where.and(QArticle.article.editor.username.eq(params.getFirst("claimedBy")));
        } else if (!currentUserHasRole(ROLE_ADMIN)) {
            where.and(QArticle.article.editor.eq(getCurrentUser()));
        }

        if (isNotEmpty(params.getFirst("query"))) {
            String query = params.getFirst("query");
            where.and(QArticle.article.title.containsIgnoreCase(query)
                    .or(QArticle.article.title.containsIgnoreCase(query)));
        }

        return where.getValue();
    }

}
