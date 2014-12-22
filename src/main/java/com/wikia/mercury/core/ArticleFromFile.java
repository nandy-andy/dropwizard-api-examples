package com.wikia.mercury.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ArticleFromFile extends Article {
    private static final String JSON_FILE = "resources/kermit.json";

    public ArticleFromFile() {
        // Jackson deserialization
    }

    public ArticleFromFile(Integer id) throws IOException {
        this.id = id;
        ObjectMapper mapper = new ObjectMapper();
        Article article = mapper.readValue(new File(JSON_FILE), ArticleFromFile.class);

        Logger.getGlobal().info(String.format(
                "Getting an article form a file, id: %d, file: %s",
                id,
                JSON_FILE
        ));

        this.id = article.getId();
        this.title = article.getTitle();
        this.thumbnail = article.getThumbnail();
        this.content = article.getContent();
    }
}
