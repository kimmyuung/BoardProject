package com.boardServiceProject.service;

import com.boardServiceProject.domain.Article;
import com.boardServiceProject.dto.ArticleDto;
import com.boardServiceProject.dto.ArticleUpdateDto;
import com.boardServiceProject.dto.UserAccountDto;
import com.boardServiceProject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시판")
@ExtendWith(MockitoExtension.class)
class ArticelServiceTest {

    @InjectMocks
    private ArticleService sut;
    // test 대상

    @Mock
    private ArticleRepository articleRepository;
    // 테스트시 객체 주입

    @DisplayName("비즈니스 로직 - 게시글 검색 시 게시글 리스트 반환")
    @Test
    void givenSearchParameters_whenSearchArticles_thenReturnArticlelist() {
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticeles(null, null, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
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

    @DisplayName("게시글 정보 후, 게시글 생성")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveArticle() {

        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // save 메소드 호출

        // When
        //sut.saveArticle(ArticleDto.of());

        // Then
        then(articleRepository).should().save(any(Article.class));
        // save를 호출했는지 테스트
    }

    @DisplayName("게시글 Id와 수정 정보를 입력하면, 게시글 수정")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdateArticle() {

        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // save 메소드 호출

        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("test", "test", "#java"));

        // Then
        then(articleRepository).should().save(any(Article.class));
        // save를 호출했는지 테스트
    }

    @DisplayName("게시글 Id 입력하면, 게시글 삭제")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle() {

        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // save 메소드 호출

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));
        // save를 호출했는지 테스트
    }
    private ArticleDto createArticleDto() {
        return createArticleDto("title", "content", "#java");
    }

    private ArticleDto createArticleDto(String title, String content, String hashtag) {
        return ArticleDto.of(
                1L,
                createUserAccountDto(),
                title,
                content,
                hashtag,
                LocalDateTime.now(),
                "Uno",
                LocalDateTime.now(),
                "Uno");
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "uno",
                "password",
                "uno@mail.com",
                "Uno",
                "This is memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }
}