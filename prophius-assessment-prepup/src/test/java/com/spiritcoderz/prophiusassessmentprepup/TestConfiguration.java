package com.spiritcoderz.prophiusassessmentprepup;

import lombok.Getter;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Getter
public abstract class TestConfiguration {
    @LocalServerPort
    protected int port;

    protected String baseUrl = "http://localhost";

    protected static RestTemplate restTemplate;

    protected static void configureTestToken(RestTemplate restTemplate){
        restTemplate.setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhb3NvYnUuZGV2QGdtYWl" +
                                    "sLmNvbSIsImlhdCI6MTcwMjk3MDIwOSwiZXhwIjoxNzEyOTcwMjA5fQ.pTcSbNOgP9EoTW0SWeIzlzyusXNCofnlICeMIvO0cms");
                    return execution.execute(request, body);
                }));
    }

}
