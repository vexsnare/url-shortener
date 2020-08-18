package com.vexsnare.urlshortener.db;

import java.util.Set;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 11:08 AM
 */
public interface IDataBase {
    public  int getHitCount(String key);
    public void storeKey(String key, String value);
    public String getValue(String key);
    public void storeKeyForUser(String uid, String key);
    public Set<String> getUserKeys(String uid);
}
