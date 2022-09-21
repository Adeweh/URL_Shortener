package com.newURL.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;


@Data
@Document("Link Shortener")
@NoArgsConstructor
public class UrlLink {
    @Id
    private String id;

    @URL
    @NotEmpty
    private String link;

}
