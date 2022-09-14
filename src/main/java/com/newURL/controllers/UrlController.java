package com.newURL.controllers;

import com.newURL.dtos.requests.RetrieveLinkRequest;
import com.newURL.dtos.requests.ShortenUrlRequest;
import com.newURL.exceptions.InvalidURLException;
import com.newURL.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shortenURL")
    public ResponseEntity<?> getShortURL(@RequestBody ShortenUrlRequest getURLRequest) {
        try {
            return new ResponseEntity<>(urlService.shortenLink(getURLRequest), HttpStatus.CREATED);
        } catch (InvalidURLException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getlong")
    public ResponseEntity<Void> getLongURL(@PathVariable String getURLRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlService.getURL(getURLRequest).getConvertedURL()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
