package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
/**
 * 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킵니다.
 * 여기서부터 SpringRunner라는 스프링 실행자를 사용합니다.
 * 즉, 스프링부트 테스트와 JUnit 사이의 연결자 <-
 */
@RunWith(SpringRunner.class)
/**
 * 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
 * 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
 * 단, @Service, @Component, @Repository 등은 사용할 수 없음
 * 여기서는 컨트롤러만 사용하기 때문에 선언 함
 */
@WebMvcTest(controllers = HelloController.class
    /**
     * CustomOAuth2UserService 등을 Scan하지 않기 떄문에 Config 도 스캔하지 않겟음
     */
    , excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    }
)
public class HelloControllerTest {

    /**
     * 스프링이 관리하는 빈(Bean)을 주입 받음
     */
    @Autowired
    /**
     * 웹 API를 테스트할 때 사용함
     * 스프링 MVC 테스트의 시작점
     * 이 클래스를 통해 Http GET, POST 등에 대한 API 테스트를 할 수 있음
     */
    private MockMvc mvc;


    @Test
    @WithMockUser(roles="USER")
    public void hello가_리턴되다() throws Exception {
        String hello = "hello";

        /**
         * MockMvc를 통해 /hello 주소로 Http GET 요청을 한다
         * 체이닝이 지원되어 아래와 같이 여러 검증기능을 이어서 선언할 수 있음
         */
        mvc.perform(get("/hello"))
                /**
                 * mvc.perform의 결과를 검증함
                 * HTTP Header의 Status를 검증함
                 * 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증함
                 * 여기선 Ok인지 즉, 200인지 아닌지를 검증함
                 */
                .andExpect(status().isOk())
                /**
                 * mvc.perform의 결과를 검증함
                 * 응답 본문의 내용을 검증함
                 * Controller에서 "hello" 를 리턴하기 때문에 값이 맞는지 검증함
                 */
                .andExpect(content().string(hello));

    }

    @Test
    @WithMockUser(roles="USER")
    public void helloDto가_리턴되다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.amount", is(amount)));
    }
}
