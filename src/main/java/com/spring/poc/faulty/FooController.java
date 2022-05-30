package com.spring.poc.faulty;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class FooController {

  @MessageMapping("foo:get")
  public Mono<String> findFoo() {
    return Mono.just("Hellow world");
  }
}
