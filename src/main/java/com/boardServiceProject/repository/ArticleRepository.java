package com.boardServiceProject.repository;

import com.boardServiceProject.domain.Article;
import com.boardServiceProject.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long>,
        QuerydslBinderCustomizer<QArticle>,
        QuerydslPredicateExecutor<Article> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);
        // 모든 컬럼을 검색하는 기능 사용 X
        bindings.including(root.title, root.content,root.hashtag, root.createdAt, root.createBy);
        // 필터를 통한 게시글 출력
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${v}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%s{v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // datetimeformat
        bindings.bind(root.createBy).first(StringExpression::containsIgnoreCase);
    }
}