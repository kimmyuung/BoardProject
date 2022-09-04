package com.boardServiceProject.repository;

import com.boardServiceProject.domain.ArticleComment;
import com.boardServiceProject.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long> ,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true);
        // 모든 컬럼을 검색하는 기능 사용 X
        bindings.including(root.content,root.createdAt, root.createBy);
        // 필터를 통한 게시글 출력
        //bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '${v}'

        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);  // like '%s{v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // datetimeformat
        bindings.bind(root.createBy).first(StringExpression::containsIgnoreCase);
    }
}