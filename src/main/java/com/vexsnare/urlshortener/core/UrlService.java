package com.vexsnare.urlshortener.core;

import com.google.inject.Inject;
import com.vexsnare.urlshortener.api.CreateUrl;
import com.vexsnare.urlshortener.db.DataBase;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 11:07 AM
 */
public class UrlService {
    @Inject
    DataBase dataBase;

    @Inject
    KeyGeneratorService keyGeneratorService;

    public String getUrl (String key) {
        return (String)dataBase.getValue(key);
    }

    public String createShortUrl(CreateUrl createUrl) {
        String key = this.keyGeneratorService.getKey();
        dataBase.storeKey(key, createUrl.getUrl());
        return key;
    }

    public boolean validateUrl(String url) {
        return true;
    }

    public boolean validateKey(String key) {
        return true;
    }
}
