package com.wikia.mercury.resources;

import com.wikia.mercury.core.Article;
import com.wikia.mercury.core.ArticleFromFile;
import com.wikia.mercury.core.ArticleFromMW;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;

import javax.ws.rs.*;

import java.io.IOException;
import java.net.URISyntaxException;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import static com.wikia.mercury.MercuryApplication.makeUri;
import static com.wikia.mercury.MercuryApplication.representationFactory;

@Path("/article/{id}")
@Produces({RepresentationFactory.HAL_JSON})
public class ArticleResource {
    Article article;

    public ArticleResource() {
        article = null;
    }

    @GET
    @Timed
    public Representation getArticle(
            @PathParam("id") Integer id,
            @QueryParam("fromFile") Optional<Boolean> fromFile
    ) throws IOException, URISyntaxException {
        if( fromFile.or(false) ) {
            article = new ArticleFromFile(id);
        } else {
            article = new ArticleFromMW(id);
        }

        Representation rep = representationFactory.newRepresentation(makeUri(ArticleResource.class));
        rep.withProperty("article", article);

        return rep;
    }
}
