package com.boardServiceProject.controller;

import controller.ArticleController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View Controller - Article")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[view][GET] Article List - Success")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnArticesView() throws Exception{
    mvc.perform(get("/articles"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // HTML과 호환되는 html도 Pass
            .andExpect(view().name("articles/index")) // 인덱스가 있는지 검사
            .andExpect(model().attributeExists("articles"));
}

    @Disabled("Need for development")
    @DisplayName("[view][GET] Article One - Success")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnArticeView() throws Exception{
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail")) // 인덱스가 있는지 검사
                .andExpect(model().attributeExists("articleComments"))
                .andExpect(model().attributeExists("article"));
    }

    @Disabled("Need for development")
    @DisplayName("[view][GET] Article Search - Success")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnArticeView() throws Exception{
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("articles/search"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

    @Disabled("Need for development")
    @DisplayName("[view][GET] Hashtag Search - Success")
    @Test
    public void givenNothing_whenRequestingHashtagSearchView_thenReturnArticeView() throws Exception{
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(view().name("articles/search-hashtag"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }
}