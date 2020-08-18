package com.vexsnare.urlshortener.resources;

import com.google.inject.Inject;
import com.vexsnare.urlshortener.api.GetUrlResponse;
import com.vexsnare.urlshortener.core.UrlService;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 10:40 AM
 */


@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class GetUrlResource {
    private static final Logger logger = Logger.getLogger(GetUrlResource.class);
    @Inject
    UrlService urlService;

    @GET
    @Path("/{key}")
    public Response redirect(@PathParam("key") String key) throws URISyntaxException {
        if(!urlService.validateKey(key)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.ACCEPTED).entity(GetUrlResponse.builder().status("302").url(urlService.getUrl(key)).build()).build();
    }
}
