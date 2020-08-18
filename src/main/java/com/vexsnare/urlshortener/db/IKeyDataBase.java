package com.vexsnare.urlshortener.db;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 11:08 AM
 */
public interface IKeyDataBase {
    public void markUsed(String key);
    public String getUnusedKey();
}
