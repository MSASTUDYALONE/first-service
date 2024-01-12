package com.example.firstserivce;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    Environment env;

    public FirstServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service";
        }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello world in First Service.";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        // 💡 port 가져오는 방법1. HttpServletRequest
        log.info("Server port={}", request.getServerPort());
        // 💡 port 가져오는 방법2. 환경변수에 할당된 정보값을 LocalServerPort로 가져오기
        return String.format("Hi, there. This is a message from First Service on PORT %s.", env.getProperty("local.server.port"));
    }
}
