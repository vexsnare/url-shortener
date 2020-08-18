package com.vexsnare.urlshortener.core;

import com.google.inject.Inject;
import com.vexsnare.urlshortener.db.DataBase;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 11:07 AM
 */
public class AnalyticsService {

    DataBase dataBase;

    @Inject
    AnalyticsService(DataBase db) {
        this.dataBase = db;
    }

    public int getHitCount(String key) {
        return dataBase.getHitCount(key);
    }
}
