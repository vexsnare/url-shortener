package com.vexsnare.urlshortener.db;

import lombok.Data;

import java.util.*;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 11:21 AM
 */

@Data
public class DataBase implements IDataBase {
    private Map<String, String> urlMap = new HashMap<>();
    private Map<String, Integer> hitCount = new HashMap<>();
    private Map<String, Set<String>> userKeys = new HashMap<>();

    @Override
    public int getHitCount(String key) {
        return hitCount.get(key);
    }

    @Override
    public void storeKey(String key, String value) {
        urlMap.put(key, value);
    }

    @Override
    public String getValue(String key) {
        hitCount.put(key, hitCount.getOrDefault(key, 0) + 1);
        return urlMap.get(key);
    }

    @Override
    public void storeKeyForUser(String uid, String key) {
        Set<String> keys = userKeys.getOrDefault(key, new HashSet<>());
        userKeys.put(key, keys);
    }

    @Override
    public Set<String> getUserKeys(String uid) {
       return userKeys.get(uid);
    }
}
