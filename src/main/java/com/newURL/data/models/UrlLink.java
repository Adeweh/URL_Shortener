package com.newURL.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("Link Shortener")
@NoArgsConstructor
public class UrlLink {
    @Id
    private String id;
    private String link;

}
