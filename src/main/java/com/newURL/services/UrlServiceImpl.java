package com.newURL.services;


import com.newURL.data.models.UrlLink;
import com.newURL.data.repositories.UrlRepository;
import com.newURL.dtos.responses.requests.ShortenUrlRequest;
import com.newURL.dtos.responses.RetrieveLinkResponse;
import com.newURL.dtos.responses.ShortenUrlResponse;
import com.newURL.utils.IdConverter;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;


    @Override
    public ShortenUrlResponse shortenLink(ShortenUrlRequest request) {
       // isValidURL(request.getLink());

        UrlLink newLink = new UrlLink();
        newLink.setLink(request.getLink());

        newLink.setId(generateId());
        urlRepository.save(newLink);

        Dotenv dotenv = Dotenv.load();


        ShortenUrlResponse response = new ShortenUrlResponse();
        response.setLink(dotenv.get("URL") + newLink.getId());
        return response;
    }

    private String generateId(){
        return IdConverter.convertKey(urlRepository.count());
    }

//    private void isValidURL(String url) throws InvalidURLException {
//        if (!url.matches("^https?:\\/\\/(?:www.)?[-a-zA-Z\\d@:%._\\+~#=]{1,256}" +
//                "\\.[a-zA-Z\\d9()]{1,6}\\b(?:[-a-zA-Z\\d@:%._\\+.~#?&\\/=]*)$")) {
//            throw new InvalidURLException("The link supplied is invalid. Please try again.");
//        }



    @Override
    public RetrieveLinkResponse getURL(String request) {
        String id = getRequestID(request);

        RetrieveLinkResponse response = new RetrieveLinkResponse();
        response.setConvertedURL(urlRepository.findUrlLinkById(id).getLink());

        return response;
    }

    private String getRequestID(String shortURL) {
        String[] compArray = shortURL.split("/");
        return compArray[compArray.length - 1];
    }

    @Override
    public long size() {
        return urlRepository.count();
    }
}