package bsr.server;

import bsr.Constants;
import com.google.gson.Gson;
import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Maciej on 2017-01-06.
 */
public class BasicAuth implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String auth = containerRequestContext.getHeaderString("Authorization");
        if (auth == null) {
            Error error = new Error("Missing auth");
            String json = new Gson().toJson(error);
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity(json).build());
        }

        String base64auth = auth.replace("Basic ", "");
        String credentials = Base64.decodeAsString(base64auth);
        if (!Constants.BANK_CREDENTIALS.equals(credentials)) {
            Error error = new Error("Bad auth");
            String json = new Gson().toJson(error);
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity(json).build());
        }
    }
}
