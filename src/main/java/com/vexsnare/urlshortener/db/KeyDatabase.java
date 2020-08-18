package com.vexsnare.urlshortener.db;

import lombok.Data;

import javax.ws.rs.NotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 11:21 AM
 */

@Data
public class KeyDatabase implements IKeyDataBase {

    Set<String> used = new HashSet<>();
    Set<String> unused = new HashSet<>();

    @Override
    public void markUsed(String key) {
        // Transaction
        // Synchronized
        synchronized (this) {
            unused.remove(key);
            used.add(key);
        }
    }

    @Override
    public String getUnusedKey() {
        if (unused.isEmpty()) {
            throw new NotFoundException("No unused key");
        }
        for (String key : unused) {
            return key;
        }
        return null;
    }

}
