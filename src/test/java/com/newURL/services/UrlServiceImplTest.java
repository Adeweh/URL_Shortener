package com.newURL.services;

import com.newURL.dtos.requests.ShortenUrlRequest;
import com.newURL.dtos.responses.ShortenUrlResponse;
import com.newURL.exceptions.InvalidURLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlServiceImplTest {
    @Autowired
    private UrlService urlService;

    ShortenUrlRequest request;

    @BeforeEach
    void setUp() {
        request = new ShortenUrlRequest();
        request.setLink("https://stackoverflow.com/questions/28920705/intellij-doesnt-work-correctly-with-cloning-project-from-github");
    }


    @Test
    public void addURL_returnShortenUrlLink() throws InvalidURLException {
        request.setLink("https://stackoverflow.com/questions/28920705/intellij-doesnt-work-correctly-with-cloning-project-from-github");
        ShortenUrlResponse response = urlService.shortenLink(request);
        assertEquals(1, urlService.size());
        assertEquals("https://newURL.com/a", response.getLink());
    }
}