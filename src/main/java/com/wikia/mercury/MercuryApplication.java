package com.wikia.mercury;

import com.wikia.mercury.resources.ArticleResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.client.HttpClientBuilder;

import org.apache.http.client.HttpClient;

import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;

public class MercuryApplication extends Application<MercuryConfiguration> {
    public static final String BASE_URI = "http://localhost:8080/";

    public static final RepresentationFactory representationFactory = new StandardRepresentationFactory()
            .withFlag(RepresentationFactory.PRETTY_PRINT);

    public static URI makeUri(final Class resourceClass) throws URISyntaxException {
        URI href = UriBuilder.fromResource(resourceClass).build();
        return new URI(MercuryApplication.BASE_URI).resolve(href);
    }

    public static void main(String[] args) throws Exception {
        new MercuryApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MercuryConfiguration> bootstrap) {
    }

    public void run(MercuryConfiguration configuration, Environment environment) {
        final HttpClient httpClient = new HttpClientBuilder(environment)
                .using(configuration.getHttpClientConfiguration())
                .build(getName());

        environment.jersey().register(httpClient);

        final ArticleResource article = new ArticleResource();
        environment.jersey().register(article);
    }
}
