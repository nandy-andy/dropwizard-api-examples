package com.wikia.mercury.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public abstract class Article {
    protected long id;
    @Length(min = 1)
    protected String title;
    protected String content;
    protected String thumbnail;

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public String getThumbnail() {
        return thumbnail;
    }
}
