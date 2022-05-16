package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 클래스 내 모든 필드의 Getter메소드를 자동 생성
 */
@Getter

/**
 * 기본 생성자 자동추가
 * public Posts(){} 와 같음
 */
@NoArgsConstructor

/**
 * 테이블과 링크될 클래스임을 나타냄
 * 기본값으로 클래스의 카멜케이스 이름을 언더스코어네이밍(_)으로 테이블 이름을 매칭함
 * ex) SalesManager.java -> sales_manager table
 */
@Entity
public class Posts extends BaseTimeEntity {

    /**
     * 해당 테이블의 PK 필드
     */
    @Id

    /**
     * PK 생성 규칙을 나타냄
     * 스프링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment 가 됨
     * 스프링부트 2.0버전과 1.5버전의 차이는 https://jojoldu.tistory.com/295 에 정리 돼있음
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨
     * 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
     * 문자열의 경우 VARCHAR(255)가 기본값ㅇ니데, 사이즈를 500으로 늘리고 싶거나, (ex: title)
     * 타입을 TEXT 로 변경하고 싶거나 (ex: content) 등의 경우에 사용됨
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /**
     * 해당 클래스의 빌드 패턴 클래스를 생성
     * 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
     */
    @Builder
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
