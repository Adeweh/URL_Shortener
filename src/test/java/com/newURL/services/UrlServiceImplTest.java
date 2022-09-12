package com.newURL.services;

import com.newURL.data.repositories.UrlRepository;
import com.newURL.dtos.requests.RetrieveLinkRequest;
import com.newURL.dtos.requests.ShortenUrlRequest;
import com.newURL.dtos.responses.RetrieveLinkResponse;
import com.newURL.dtos.responses.ShortenUrlResponse;
import com.newURL.exceptions.InvalidURLException;
import org.junit.jupiter.api.AfterEach;
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

    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    void setUp() {
        request = new ShortenUrlRequest();
        request.setLink("https://stackoverflow.com/questions/28920705/intellij-doesnt-work-correctly-with-cloning-project-from-github");
    }

    @AfterEach
    void tearDown() {
        urlRepository.deleteAll();

    }

    @Test
    public void addURL_returnShortenUrlLink() throws InvalidURLException {
        request.setLink("https://stackoverflow.com/questions/28920705/intellij-doesnt-work-correctly-with-cloning-project-from-github");
        ShortenUrlResponse response = urlService.shortenLink(request);
        assertEquals(1, urlService.size());
        assertEquals("https://newURL.com/a", response.getLink());
    }

    @Test
    void testThatShortURLCanRetrieveLongURL() throws InvalidURLException {
        request.setLink("https://stackoverflow.com/questions/28920705/intellij-doesnt-work-correctly-with-cloning-project-from-github");
        ShortenUrlResponse response = urlService.shortenLink(request);

        RetrieveLinkRequest request1 = new RetrieveLinkRequest();
        request1.setConvertedURL(response.getLink());
        RetrieveLinkResponse response1 = urlService.getURL(request1);

        assertEquals("https://stackoverflow.com/questions/28920705/intellij-doesnt-work-correctly-with-cloning-project-from-github", response1.getConvertedURL());


    }
}