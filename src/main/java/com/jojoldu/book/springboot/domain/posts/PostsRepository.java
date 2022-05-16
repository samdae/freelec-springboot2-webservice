package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    /**
     * 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해
     * 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용합니다.
     *
     * 대표적 예로 querydsl, jooq, MyBatis 등이 있습니다.
     * 조회는 위 3가지 프레임워크 중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJpa를 통해 진행합니다.
     *
     * 개인적으로는 querydsl를 추천합니다. Querydsl을 추천하는 이유는 다음과 같습니다.
     *
     * 1. 타입 안정성이 보장됩니다.단순한 문자열로 쿼리를 생성하는 것이 아니라,
     * 메소드를 기반으로 쿼리를 생성하기 때문에 오타나 존재하지 않는 컬럼명을 명시할 경우 IDE에서 자동으로 검출됩니다.
     * 이 장점은 Jooq에서도 지원하는 장점이지만, MyBatis에서는 지원하지 않습니다.
     *
     * 2. 국내 많은 회사에서 사용 중입니다.쿠팡, 배민 등 JPA를 적극적으로 사용하는 회사에서는 Querydsl를 적극적으로 사용 중입니다.
     *
     * 3. 레퍼런스가 많습니다.
     * 앞 2번의 장점에서 이어지는 것인데, 많은 회사와 개발자들이 사용하다보니 그만큼 국내 자료가 많습니다.
     * 어떤 문제가 발생했을 때 여러 커뮤니티에 질문하고 그에 대한 답변을 들을 수 있다는 것은 큰 장점입니다.
     */

    /**
     * SpringDataJpa 에서 제공하지 않는 메소드는 이 처럼 쿼리로 작성해도 사용 가능
     */
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
