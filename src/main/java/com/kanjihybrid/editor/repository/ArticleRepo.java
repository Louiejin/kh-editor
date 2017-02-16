package com.kanjihybrid.editor.repository;

import com.kanjihybrid.editor.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long>, QueryDslPredicateExecutor<Article> {

}
