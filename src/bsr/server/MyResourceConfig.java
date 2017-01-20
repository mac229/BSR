package bsr.server;

import bsr.server.providers.MyJsonMappingException;
import bsr.server.providers.MyJsonProcessingException;
import bsr.server.providers.MyUnrecognizedPropertyException;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Maciej on 2017-01-06.
 */
public class MyResourceConfig extends ResourceConfig {

    public MyResourceConfig() {
        packages("bsr.server.rest");
        register(JacksonJaxbJsonProvider.class);
        register(BasicAuth.class);
        register(MyUnrecognizedPropertyException.class);
        register(MyJsonMappingException.class);
        register(MyJsonProcessingException.class);
    }
}