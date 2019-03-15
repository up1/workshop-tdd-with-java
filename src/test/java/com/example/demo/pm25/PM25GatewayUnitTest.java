package com.example.demo.pm25;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class PM25GatewayUnitTest {

    private PM25Gateway subject;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new PM25Gateway(restTemplate, "http://localhost:8089");
    }

    @Test
    public void shouldCallPM25API() throws Exception {
        // Create Stub
        PM25Response expectedResponse = new PM25Response(1000);
        given(restTemplate.getForObject("http://localhost:8089/feed/thailand/pathum-thani/bangkok-university-rangsit-campus/?token=6852ade641231a1c26a136549a9e0952b8a30f91", PM25Response.class))
                .willReturn(expectedResponse);

        // Act
        Optional<PM25Response> actualResponse = subject.fetchData();

        // Assert
        assertThat(actualResponse, is(Optional.of(expectedResponse)));
    }

    @Test
    public void shouldReturnEmptyOptionalIfPM25APIIsUnavailable() {
        // Create Stub
        given(restTemplate.getForObject("http://localhost:8089/feed/thailand/pathum-thani/bangkok-university-rangsit-campus/?token=6852ade641231a1c26a136549a9e0952b8a30f91", PM25Response.class))
                .willThrow(new RestClientException("something went wrong"));

        // Act
        Optional<PM25Response> actualResponse = subject.fetchData();

        // Assert
        assertThat(actualResponse, is(Optional.empty()));

    }

}