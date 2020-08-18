package com.vexsnare.urlshortener;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import com.google.inject.Stage;

public class UrlShortenerApplication extends Application<UrlShortenerConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerApplication.class);
    private GuiceBundle<UrlShortenerConfiguration> guiceBundle;
    public static void main(final String[] args) throws Exception {
        new UrlShortenerApplication().run(args);
    }

    @Override
    public String getName() {
        return "url-shortener";
    }

    @Override
    public void initialize(final Bootstrap<UrlShortenerConfiguration> bootstrap) {
        guiceBundle = new GuiceBundle.Builder<UrlShortenerConfiguration>()
                .modules(new UrlShortenerModule(bootstrap.getMetricRegistry()))
                .enableAutoConfig(getClass().getPackage().getName())
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final UrlShortenerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
