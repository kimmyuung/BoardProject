package com.boardServiceProject;

import com.boardServiceProject.config.JpaConfig;
import com.boardServiceProject.domain.Article;
import com.boardServiceProject.repository.ArticleCommentRepository;
import com.boardServiceProject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("testdb") // test db 사용
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// 테스트 db 불러오지 않고 사용 로컬안의 db 사용
@DisplayName("Jpa Connect Test")
@Import(JpaConfig.class)
@DataJpaTest
public class JpaRepositoryTest {

    private final ArticleRepository articleRepository;

    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleCommentRepository = articleCommentRepository;
        this.articleRepository = articleRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelection_thenWorksFine() {
        // Given


        // When
        List<Article> articleList = articleRepository.findAll();

        // Then
        assertThat(articleList)
                .isNotNull()
                .hasSize(120);
    }
    @DisplayName("Insert test")
    @Test
    void givenTestData_whenInsert_thenWorkFine() {
        // Given
        long previousCount = articleRepository.count();

        // When
        Article article = articleRepository.save(Article.of("new article", "new Content", "#test"));
        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("Updateing test")
    @Test
    void givenTestData_whenUpdate_thenWorkFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#springboot";
        article.setHashtag(updateHashtag);
        // When
        Article updatearticle = articleRepository.saveAndFlush(article);
        // h2는 종료되면 초기화되기 때문에 rollback 되기 때문에 saveAndFlush를 해야 반영
        // db에 반영하려 하지만 결과에 영향은 없음

        // Then
        assertThat(updatearticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);
    }
    @DisplayName("Deleteing test")
    @Test
    void givenTestData_whenDelete_thenWorkFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousCount = articleRepository.count();
        long previousCommentCount = articleCommentRepository.count();
        int deleteCommentsize = article.getArticleComments().size();
        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousCommentCount - deleteCommentsize);

    }
}
