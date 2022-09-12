package com.boardServiceProject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Entity
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createBy")
        })
// @EntityListeners(AuditingEntityListener.class)
public class Article extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId") private UserAccount userAccount; // 유저 정보 (ID)

    @Setter @Column(nullable = false)
    private String title; // 제목
    @Setter @Column(nullable = false, length = 10000)
    private String content; // 본문
    @Setter
    private String hashtag; // 해시태크

    @ToString.Exclude // 없을 시에 순환참조로 인한 무한루프 발생
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

//    @CreatedDate
//    private LocalDateTime createdAt; // 생성일시
//
//    @CreatedBy @Column(nullable = false)
//    private String createBy; // 생성자
//
//    @LastModifiedDate @Column(nullable = false, length = 100)
//    private LocalDateTime modifiedAt; // 수정일시
//
//    @LastModifiedBy @Column(nullable = false, length = 100)
//    private String modifiedBy; // 수정자
//
//    @Embedded AAA aa; // 묶을 컬럼들 추출
//    class AAA {
//        // 묶을 자료들 삽입
//    }
    protected Article() {}

    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
