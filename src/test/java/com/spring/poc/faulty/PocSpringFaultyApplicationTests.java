package com.spring.poc.faulty;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles("test")
class PocSpringFaultyApplicationTests {

  private static RSocketRequester requester;

  @BeforeAll
  static void setUpOnce(@Autowired RSocketRequester.Builder builder, @LocalRSocketServerPort Integer port) {
    requester = builder.tcp("localhost", port);
  }

  @Test
  void contextLoads() {
    StepVerifier.create(
            requester
                .route("foo:get")
                .retrieveMono(String.class))
        .assertNext(response -> System.out.println(response))
        .verifyComplete();

  }
}
