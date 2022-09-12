package com.newURL.data.repositories;

import com.newURL.data.models.UrlLink;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlLink, String> {
    UrlLink findUrlLinkById(String id);
}
