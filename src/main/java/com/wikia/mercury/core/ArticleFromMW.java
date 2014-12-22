package com.wikia.mercury.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

import java.net.URL;
import java.util.logging.Logger;

public class ArticleFromMW extends Article {
    private static final String API_MERCURY_URL = "http://muppet.wikia.com/api/v1/Mercury/Article?id=";

    public ArticleFromMW() {
        // Jackson deserialization
    }

    public ArticleFromMW(Integer id) throws IOException {
        this.id = id;
        String url = API_MERCURY_URL + id;

        ObjectMapper mapper = new ObjectMapper();

        Logger.getGlobal().info(String.format(
                "Getting an article form a MW API response, id: %d, url: %s",
                id,
                url
        ));

        ObjectNode root = (ObjectNode) mapper.readTree(new URL(url));

        this.id = root.findValue("id").asInt();
        this.title = root.findValue("title").asText();
        this.thumbnail = root.findValue("thumbnail").asText();
        this.content = root.findValue("content").asText();
    }
}
