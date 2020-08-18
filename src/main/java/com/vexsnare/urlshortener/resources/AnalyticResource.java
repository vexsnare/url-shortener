package com.vexsnare.urlshortener.resources;

import com.google.inject.Inject;
import com.vexsnare.urlshortener.api.AnalyticResponse;
import com.vexsnare.urlshortener.api.Status;
import com.vexsnare.urlshortener.core.AnalyticsService;
import com.vexsnare.urlshortener.core.UrlService;
import com.vexsnare.urlshortener.db.DataBase;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 10:39 AM
 */
public class AnalyticResource {

    @Inject
    AnalyticsService analyticsService;

    @Inject
    UrlService urlService;

    @GET
    @Path("/{key}")
    public Response getHitCount(@PathParam("key") String key) {
        if(!urlService.validateKey(key)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            int count = analyticsService.getHitCount(key);
            return Response.status(Response.Status.ACCEPTED).entity(
                    AnalyticResponse.builder().count(count).status(Status.SUCCESS.toString()).key(key).build()
            ).build();
        }
    }
}
