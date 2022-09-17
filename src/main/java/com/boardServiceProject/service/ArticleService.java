package com.boardServiceProject.service;

import com.boardServiceProject.domain.Article;
import com.boardServiceProject.domain.type.SearchType;
import com.boardServiceProject.dto.ArticleDto;
import com.boardServiceProject.dto.ArticleUpdateDto;
import com.boardServiceProject.dto.ArticleWithCommentsDto;
import com.boardServiceProject.dto.UserAccountDto;
import com.boardServiceProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticeles(SearchType title, String search_keyword, Pageable pageable) {
        if(search_keyword == null || search_keyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch(title) {
            case TITLE -> articleRepository.findByTitleContaining(search_keyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(search_keyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(search_keyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(search_keyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag(search_keyword, pageable).map(ArticleDto::from);
        };
        // 레포스토리에서 검색 기능 구현
    }

    @Transactional(readOnly = true)
    public ArticleDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
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
        try {
            Article article = articleRepository.getReferenceById(l);
            if (of.title() != null) {
                article.setTitle(of.title());
            }
            if (of.content() != null) {
                article.setContent(of.content());
            }
            article.setHashtag(of.hashtag());
            articleRepository.save(article);
        }catch (EntityNotFoundException e) {
            System.out.println("업데이트 실패! 게시글 실패 " + e);
        }
    }

    public void deleteArticle(long l) {
        articleRepository.deleteById(l);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticlesViaHashtag(String hashtag, Pageable pageable) {
        if (hashtag == null || hashtag.isBlank()) {
            return Page.empty(pageable);
        }

        return articleRepository.findByHashtag(hashtag, pageable).map(ArticleDto::from);
    }

    public List<String> getHashtags() {
        return articleRepository.findAllDistinctHashtags();
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

    public Object getArticleWithComments(Long articleId) {
        return null;
    }
}
