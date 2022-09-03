package com.boardServiceProject.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleComment {
    private Long id; // 작성 아이디
    private Article article; // 게시글 아이디
    private String content;

    private LocalDateTime createdAt;
    private String createBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;


}
