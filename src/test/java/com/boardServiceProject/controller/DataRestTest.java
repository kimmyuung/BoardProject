package com.boardServiceProject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Disabled("Spring Data Rest TotalTest is not essential")
@DisplayName("Data Rest : API Test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
        //(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DataRestTest {
    private final MockMvc mvc;
    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("Api Test : Article List ")
    @Test
    void givenNothing_WhenRequestingArticles_thenReturnArticlesJsonResponse() throws Exception{
        // Given

        // When & Then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
                //.andDo(print());
    }
    @DisplayName("Api Test : Article One")
    @Test
    void givenNothing_WhenRequestingArticle_thenReturnArticlesJsonResponse() throws Exception{
        // Given

        // When & Then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //.andDo(print());
    }

    @DisplayName("Api Test : ArticleComment List")
    @Test
    void givenNothing_WhenRequestingArticleCommentList_thenReturnArticleCommentListJsonResponse() throws Exception{
        // Given

        // When & Then
        mvc.perform(get("/api/articles/1/articlecomments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //.andDo(print());
    }

    @DisplayName("Api Test : ArticleComment One")
    @Test
    void givenNothing_WhenRequestingArticleComment_thenReturnArticleCommentJsonResponse() throws Exception{
        // Given

        // When & Then
        mvc.perform(get("/api/articlecomments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //.andDo(print());
    }


}
