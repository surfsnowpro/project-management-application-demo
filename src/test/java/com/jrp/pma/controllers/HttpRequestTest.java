package com.jrp.pma.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// using webEnvironment since we want to generate the http for our home page
// creates tomcat server with, in this case, a random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class HttpRequestTest {

    // get the port number from our webEnvironment and store it
    @LocalServerPort
    private int port;

    // Mimics basic http request
    // used to GET resources (web pages), POJOs, JSON, headers, create resource (POST)
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void homePageReturnsVersionNumberCorrectly_thenSuccess() {
        String renderedHtml = restTemplate.getForObject("http://localhost:" + port + "/", String.class);

        assertTrue(renderedHtml.contains("0.1.0"));
    }
}
