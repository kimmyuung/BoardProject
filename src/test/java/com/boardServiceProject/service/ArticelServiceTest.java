package com.boardServiceProject.service;

import com.boardServiceProject.domain.type.SearchType;
import com.boardServiceProject.dto.ArticleDto;
import com.boardServiceProject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ArticelServiceTest {

    @InjectMocks
    private ArticelService sut;
    // test 대상

    @Mock
    private ArticleRepository articleRepository;
    // 테스트시 객체 주입

    @DisplayName("비즈니스 로직 - 게시글 검색 시 게시글 리스트 반환")
    @Test
    void givenSearchParameters_whenSearchArticles_thenReturnArticlelist() {
        // Given

        // When
        Page<ArticleDto> articleDtoList =
            sut.searchArticeles(SearchType.TITLE, "keyword"); // 제목, 본문, 아이디, 닉네임, 해시태그

        // Then
        assertThat(articleDtoList).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환")
    @Test
    void givenArticleId_whenSearchArticle_thenReturnArticle() {
        // Given

        // When

        ArticleDto articleDto =
                sut.searchArtice(1L);

        // Then
        assertThat(articleDto).isNotNull();
    }
}