package com.newURL.services;

import com.newURL.dtos.requests.RetrieveLinkRequest;
import com.newURL.dtos.requests.ShortenUrlRequest;
import com.newURL.dtos.responses.RetrieveLinkResponse;
import com.newURL.dtos.responses.ShortenUrlResponse;
import com.newURL.exceptions.InvalidURLException;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    ShortenUrlResponse shortenLink(ShortenUrlRequest request) throws InvalidURLException;

    RetrieveLinkResponse getURL(RetrieveLinkRequest request) throws InvalidURLException;


    long size();
}
