package com.newURL.services;


import com.newURL.data.models.UrlLink;
import com.newURL.data.repositories.UrlRepository;
import com.newURL.dtos.requests.RetrieveLinkRequest;
import com.newURL.dtos.requests.ShortenUrlRequest;
import com.newURL.dtos.responses.RetrieveLinkResponse;
import com.newURL.dtos.responses.ShortenUrlResponse;
import com.newURL.exceptions.InvalidURLException;
import com.newURL.utils.IdConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Override
    public ShortenUrlResponse shortenLink(ShortenUrlRequest request) throws InvalidURLException {
        isValidURL(request.getLink());

        UrlLink newLink = new UrlLink();
        newLink.setLink(request.getLink());
        newLink.setId(String.valueOf(urlRepository.count()));
        urlRepository.save(newLink);

        String subDir = getSubDirectory(newLink.getId());
        ShortenUrlResponse response = new ShortenUrlResponse();
        response.setLink("localhost:8080/" + subDir);
        return response;


    }

    private String getSubDirectory(String id){
        int requestId = Integer.parseInt(id);
        return IdConverter.convertKey(requestId);
    }

    private void isValidURL(String url) throws InvalidURLException {
        if (!url.matches("^https?:\\/\\/(?:www.)?[-a-zA-Z\\d@:%._\\+~#=]{1,256}" +
                "\\.[a-zA-Z\\d9()]{1,6}\\b(?:[-a-zA-Z\\d@:%._\\+.~#?&\\/=]*)$")) {
            throw new InvalidURLException("The link supplied is invalid. Please try again.");
        }
    }

    @Override
    public RetrieveLinkResponse getURL(String request) {
        String id = String.valueOf(getRequestID(request));

        RetrieveLinkResponse response = new RetrieveLinkResponse();
        response.setConvertedURL(urlRepository.findUrlLinkById(id).getLink());

        return response;
    }

    private int getRequestID(String shortURL) {
        String[] compArray = shortURL.split("/");
        String subDir = compArray[compArray.length - 1];
        return IdConverter.getKey(subDir);
    }

    @Override
    public long size() {
        return urlRepository.count();
    }
}