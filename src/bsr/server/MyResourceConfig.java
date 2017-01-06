package bsr.server;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Maciej on 2017-01-06.
 */
public class MyResourceConfig extends ResourceConfig {


    public MyResourceConfig() {
        packages("bsr.server.rest");
        register(JacksonJaxbJsonProvider.class);
        register(BasicAuth.class);
    }
}