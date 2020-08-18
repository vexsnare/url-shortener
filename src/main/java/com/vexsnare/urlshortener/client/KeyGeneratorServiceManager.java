package com.vexsnare.urlshortener.client;

import com.google.inject.Inject;
import com.vexsnare.urlshortener.core.KeyGeneratorService;
import com.vexsnare.urlshortener.db.KeyDatabase;
import io.dropwizard.lifecycle.Managed;

import java.util.concurrent.Semaphore;

/**
 * @author vinay.saini
 * @created 18/08/2020 - 1:58 PM
 */
public class KeyGeneratorServiceManager implements Managed {
    private KeyGeneratorService keyGeneratorService;
    private KeyDatabase keyDatabase;

    @Inject
    KeyGeneratorServiceManager(KeyGeneratorService keyGeneratorService, KeyDatabase keyDatabase) {
        this.keyGeneratorService = keyGeneratorService;
        this.keyDatabase = keyDatabase;
    }

    @Override
    public void start() throws Exception {
        Semaphore blocker = new Semaphore(1);
        blocker.acquire();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            blocker.release();
        }));
        Thread t = new Thread(keyGeneratorService);
        t.start();
    }

    @Override
    public void stop() throws Exception {

    }
}
