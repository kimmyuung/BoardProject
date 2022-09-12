package com.boardServiceProject.service;

import com.boardServiceProject.domain.type.SearchType;
import com.boardServiceProject.dto.ArticleDto;
import com.boardServiceProject.dto.ArticleUpdateDto;
import com.boardServiceProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Transactional
@Service
public class ArticelService {

    private final ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticeles(SearchType title, String search_keyword) {
        return Page.empty();
        // 레포스토리에서 검색 기능 구현
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArtice(long articleId) {
        return null;
        // 레포스토리에서 검색 기능 구현
    }

    public void saveArticle(ArticleDto dto) {

    }

    public void updateArticle(long l, ArticleUpdateDto of) {

    }

    public void deleteArticle(long l) {
    }
}
