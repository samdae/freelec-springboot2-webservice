package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 이 어노테이션이 생성될 수 있는 위치를 지정
 * PARAMETER 로 지정했으니 메서드의 파라미터로 선언된 객체에서만 사용할 수 있음
 */
@Target(ElementType.PARAMETER)

/**
 * 이 파일을 어노테이션 클래스로 지정함
 * LoginUser 라는 이름을 가진 어노테이션이 생성됐다고 보면 됨
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
/**
 * 본 서비스는 2번의 방법 (RDB Session) 을 통해 진행 해 봄
 */

/**
 * 지금 우리가 만든 서비스는 애플리케이션을 재실행하면 로그인이 풀린다. 왜? 이는 세션이 내장 톰캣의 메모리에 저장되기 때문.
 * 기본적으로 세션은 실행되는 WASWeb Application Server의 메모리에서 저장되고 호출됨.
 * 메모리에 저장되다 보니 내장 톰캣처럼 애플리케이션 실행 시 실행되는 구조에선 항상 초기화가 됨.
 * 즉, 배포할 때마다 톰캣이 재시작되는 것.
 *
 * 이 외에도 한 가지 문제가 더 있다.
 * 2대 이상의 서버에서 서비스하고 있다면 톰캣마다 세션 동기화 설정을 해야만 한다.
 * 그래서 실제 현업에서는 세션 저장소에 대해 다음의 3가지 중 한 가지를 선택합니다.
 *
 * (1) 톰캣 세션을 사용한다.
 *      일반적으로 별다른 설정을 하지 않을 때 기본적으로 선택되는 방식.
 *      이렇게 될 경우 톰캣(WAS)에 세션이 저장되기 때문에 2대 이상의 WAS가 구동되는 환경에서는
 *      톰캣들 간의 세션 공유를 위한 추가 설정이 필요.
 * (2) MySQL과 같은 데이터베이스를 세션 저장소로 사용한다.
 *      여러 WAS 간의 공용 세션을 사용할 수 있는 가장 쉬운 방법임.
 *      많은 설정이 필요 없지만, 결국 로그인 요청마다 DB IO가 발생하여 "성능상 이슈"가 발생할 수 있음.
 *      보통 로그인 요청이 많이 없는 백오피스, 사내 시스템 용도에서 사용.
 * (3) Redis, Memcached와 같은 메모리 DB를 세션 저장소로 사용한다.
 *      B2C 서비스에서 가장 많이 사용하는 방식.
 *      실제 서비스로 사용하기 위해서는 Embedded Redis와 같은 방식이 아닌 외부 메모리 서버가 필요.
 */




