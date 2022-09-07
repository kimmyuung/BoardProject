package com.boardServiceProject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createBy")
})
//@EntityListeners(AuditingEntityListener.class)
public class ArticleComment extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 작성 아이디
    @Setter
    @ManyToOne(optional = false)
    private Article article; // 게시글 아이디

    @Setter @Column(nullable = false, length = 500)
    private String content;

//    @CreatedDate
//    private LocalDateTime createdAt; // 생성일시
//
//    @CreatedBy
//    @Column(nullable = false)
//    private String createBy; // 생성자
//
//    @LastModifiedDate
//    @Column(nullable = false, length = 100)
//
//    private LocalDateTime modifiedAt; // 수정일시
//
//    @LastModifiedBy
//    @Column(nullable = false, length = 100)
//    private String modifiedBy; // 수정자

    protected ArticleComment() {}

    private ArticleComment(Article article,String content) {
        this.article = article;
        this.content = content;

    }
    public static ArticleComment of(Article article,String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
