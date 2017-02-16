package com.kanjihybrid.editor.mapper;

import com.kanjihybrid.editor.dto.ArticleDto;
import com.kanjihybrid.editor.model.Article;
import com.kanjihybrid.editor.repository.UserRepo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.kanjihybrid.editor.util.MapperUtil.notExcluded;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @author Frank Lloyd Teh
 */
@Component
public class ArticleMapper {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "userRepo")
    private UserRepo userRepo;

    public ArticleDto map(Article article, String... excludes) {
        return map(article, new ArticleDto(), excludes);
    }

    public ArticleDto map(Article article, ArticleDto dto, String... excludes) {
        copyProperties(article, dto, "editor", "body", "translatedBody");

        if (notExcluded(excludes, "editor") && article.getEditor() != null)
            dto.setEditor(userMapper.map(article.getEditor()));

        if (notExcluded(excludes, "body") && article.getBody() != null)
            dto.setBody(article.getBody());

        if (notExcluded(excludes, "translatedBody") && article.getTranslatedBody() != null)
            dto.setTranslatedBody(article.getTranslatedBody());

        return dto;
    }

    public Article map(ArticleDto dto, String... excludes) {
        return map(dto, new Article(), excludes);
    }

    public Article map(ArticleDto dto, Article article, String... excludes) {
        copyProperties(dto, article, "editor");

        if (notExcluded(excludes, "editor") && dto.getEditor() != null)
            article.setEditor(userRepo.getOne(dto.getEditor().getId()));

        if (notExcluded(excludes, "body") && dto.getBody() != null)
            article.setBody(dto.getBody());

        if (notExcluded(excludes, "translatedBody") && dto.getTranslatedBody() != null)
            article.setTranslatedBody(dto.getTranslatedBody());

        return article;
    }

}

