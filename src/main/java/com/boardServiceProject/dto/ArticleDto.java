package com.boardServiceProject.dto;

import java.time.LocalDateTime;

public record ArticleDto(
        LocalDateTime createdAt,
        String createBy,
        String title,
        String content,
        String hashtag
) /*implements Serializable*/ {
// Serializable : 자바를 통해 직렬화시 사용
// 잭슨을 이용하기 때문에 JPA Buddy를 만들면서 자동으로 삽입
    // json 사용할 것이기 때문에 삭제
    public static ArticleDto of(LocalDateTime createdAt,
                      String createBy,
                      String title,
                      String content,
                      String hashtag) {
      return new ArticleDto(createdAt, createBy, title, content, hashtag);
    }
}
