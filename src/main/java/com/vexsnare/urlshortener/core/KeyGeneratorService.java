package com.vexsnare.urlshortener.core;

import com.google.inject.Inject;
import com.vexsnare.urlshortener.cli.RandomStringGenerator;
import com.vexsnare.urlshortener.db.IKeyDataBase;
import com.vexsnare.urlshortener.db.KeyDatabase;

import java.util.HashSet;
import java.util.Set;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 11:07 AM
 */

public class KeyGeneratorService implements Runnable {

    KeyDatabase keyDatabase;
    @Inject
    KeyGeneratorService(KeyDatabase k) {
        this.keyDatabase = k;
    }

    int MAX = 10;

    public String getKey() {
        String key = keyDatabase.getUnusedKey();
        keyDatabase.markUsed(key);
        return key;
    }

    public void generateKeys() {
        int count = 0;
        if(keyDatabase.getUnused() == null || keyDatabase.getUnused().size() >= MAX) {
            return;
        }
        while (count < MAX) {
            String hash = RandomStringGenerator.getAlphaNumericString(8);
            if(keyDatabase.getUsed().contains(hash)) {
                continue;
            } else {
                keyDatabase.getUnused().add(hash);
            }
        }
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1*1000);
            this.generateKeys();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
