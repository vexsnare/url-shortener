package com.vexsnare.urlshortener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.vexsnare.urlshortener.db.DataBase;
import com.vexsnare.urlshortener.db.IDataBase;
import com.vexsnare.urlshortener.db.IKeyDataBase;
import com.vexsnare.urlshortener.db.KeyDatabase;
import io.dropwizard.Configuration;

public class UrlShortenerModule extends AbstractModule {
    private final MetricRegistry metricRegistry;

    public UrlShortenerModule(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
        JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
    }

    @Override
    protected void configure() {
        bind(IKeyDataBase.class).to(KeyDatabase.class).in(Singleton.class);
        bind(IDataBase.class).to(DataBase.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public KeyDatabase provideKeyDatabase() {
        return new KeyDatabase();
    }



}
