package com.vexsnare.urlshortener.resources;

import com.google.inject.Inject;
import com.vexsnare.urlshortener.api.CreateUrl;
import com.vexsnare.urlshortener.api.CreateUrlResponse;
import com.vexsnare.urlshortener.api.Status;
import com.vexsnare.urlshortener.core.UrlService;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 10:38 AM
 */

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class CreateUrlResource {

    private static final Logger logger = Logger.getLogger(CreateUrlResource.class);
    @Inject
    UrlService urlService;

    @POST
    @Path("/create/{uid}")
    public Response submit(@PathParam("uid") String uid, @NotNull CreateUrl createUrl) {
        if(urlService.validateUrl(createUrl.getUrl())) {
            String shortUrl = urlService.createShortUrl(createUrl);
            return Response.status(200).entity(CreateUrlResponse.builder().status(Status.SUCCESS.toString()).url(shortUrl).build()).build();
        } else return Response.status(Response.Status.BAD_REQUEST).build();
    }


}
