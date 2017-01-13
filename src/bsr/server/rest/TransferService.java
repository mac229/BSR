package bsr.server.rest;

import bsr.server.model.Transfer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Maciej on 2017-01-06.
 */
@Path("/transfer")
public class TransferService {

    // This method is called if XMLis request
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer getTransfer() {
        return new Transfer("title", 100, "sender", "receiver");
    }
}
