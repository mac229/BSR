package bsr.server;

import bsr.Constants;
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
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("TODO ERROR - missing auth").build());
        }

        String base64auth = auth.replace("Basic ", "");
        String credentials = Base64.decodeAsString(base64auth);
        if (!Constants.BANK_CREDENTIALS.equals(credentials)) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("TODO ERROR - wrong credentials").build());
        }
    }
}
