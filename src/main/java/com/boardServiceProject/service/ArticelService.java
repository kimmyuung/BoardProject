package com.boardServiceProject.service;

import com.boardServiceProject.domain.Article;
import com.boardServiceProject.domain.type.SearchType;
import com.boardServiceProject.dto.ArticleDto;
import com.boardServiceProject.dto.ArticleUpdateDto;
import com.boardServiceProject.dto.ArticleWithCommentsDto;
import com.boardServiceProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Transactional
@Service
public class ArticelService {

    private final ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticeles(SearchType title, String search_keyword, Pageable pageable) {
        if(search_keyword == null || search_keyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch(title) {
            case TITLE -> articleRepository.findByTitleContaining(search_keyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(search_keyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_USERIdContaining(search_keyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(search_keyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashTag("#" + search_keyword, pageable).map(ArticleDto::from);
        };
        // 레포스토리에서 검색 기능 구현
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto searchArtice(long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId : "));
        // 레포스토리에서 검색 기능 구현
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(long l, ArticleUpdateDto of) {
        Article article = articleRepository.getReferenceById(l);
        if(of.title() != null ) {article.setTitle(of.title());}
        if(of.content() != null) { article.setContent(of.content());}
        article.setHashtag(of.hashtag());
        articleRepository.save(article);
    }

    public void deleteArticle(long l) {
        articleRepository.deleteById(l);
    }

}
