package com.example.demo.pm25;

import com.example.demo.FileLoader;
import com.example.demo.group.IntegrationTests;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PM25GatewayIntegrationTest {

    @Autowired
    private PM25Gateway subject;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void shouldCallPM25APIWithSuccess() throws Exception {
        // Create Fake API with WireMock
        wireMockRule.stubFor(get(urlPathEqualTo("/feed/thailand/pathum-thani/bangkok-university-rangsit-campus/"))
                .withQueryParam("token", matching("6852ade641231a1c26a136549a9e0952b8a30f91"))
                .willReturn(aResponse()
                        .withBody(FileLoader.read("classpath:pm25Response.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        // Call API
        Optional<PM25Response> pm25Response = subject.fetchData();

        // Assert :: Check response from API
        Optional<PM25Response> expectedResponse = Optional.of(new PM25Response(1000));
        assertThat(pm25Response, is(expectedResponse));
    }

}