package com.jojoldu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing 어노테이션들이 '모두' 활성화 될 수 있도록 main 에 설정
 * Entity가 활성화 된 곳에서 Scan할 수 있게 끔 Application에서 분리 해준다.
 */
@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
