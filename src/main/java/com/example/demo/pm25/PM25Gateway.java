package com.example.demo.pm25;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class PM25Gateway {

    private final RestTemplate restTemplate;
    private final String pm25Url;

    public PM25Gateway(final RestTemplate restTemplate,  @Value("${pm25.url}") final String pm25Url
    ) {
        this.restTemplate = restTemplate;
        this.pm25Url = pm25Url;
    }

    public Optional<PM25Response> fetchData() {
        String url = pm25Url + "/feed/thailand/pathum-thani/bangkok-university-rangsit-campus/?token=6852ade641231a1c26a136549a9e0952b8a30f91";

        try {
            return Optional.ofNullable(restTemplate.getForObject(url, PM25Response.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
