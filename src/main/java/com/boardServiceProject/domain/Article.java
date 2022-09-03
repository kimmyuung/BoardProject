package com.boardServiceProject.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@ToString
public class Article {
    private Long id;
    private String title; // 제목
    private String content; // 본문
    private String hashtag; // 해시태크

    private LocalDateTime createdAt; // 생성일시
    private String createBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일시
    private String modifiedBy; // 수정자



}
