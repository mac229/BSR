package bsr.server.rest;

import bsr.Constants;
import bsr.server.Data;
import bsr.server.Utils;
import bsr.server.exception.NotFound;
import bsr.server.model.Config;
import bsr.server.model.Error;
import bsr.server.model.Payment;
import bsr.server.model.Transfer;
import bsr.server.soap.BankService;
import com.google.gson.Gson;
import org.glassfish.jersey.internal.util.Base64;
import sun.misc.IOUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Maciej on 2017-01-06.
 */
@Path("/transfer")
public class TransferService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransfer(Transfer transfer) {
        Data data = Data.getInstance();
        Gson gson = new Gson();

        Payment payment = new Payment(transfer.getAmount(), transfer.getReceiver());
        try {
            new BankService().paymentIn(payment);
        } catch (NotFound notFound) {
            Error error = new Error("Invalid bill number");
            String json = gson.toJson(error);
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(json).build());
        }
        Utils.save(data);

        return Response.created(null).build();
    }

    public void transfer(Transfer transfer) throws IOException {
        String bankId = transfer.getReceiver().substring(2, 10);
        Config config = Utils.getConfig();
        String url = config.getAddresses().get(bankId) + "/transfer";

        String data = new Gson().toJson(transfer);
        System.out.println(data);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", Constants.UTF_8);
        connection.setRequestProperty("Content-Type", "application/json;charset=" + Constants.UTF_8);
        connection.setRequestProperty("Authorization", "Basic " + Base64.encodeAsString(config.getCredentials()));

        OutputStream requestBody = connection.getOutputStream();
        requestBody.write(data.getBytes(Constants.UTF_8));
        requestBody.close();
        connection.connect();

        int code = connection.getResponseCode();
        if (code >= 200 && code < 300) {
            handleSuccessResponse(connection);
        } else {
            System.out.println("FAILED CODE " + code);
        }
    }

    private void handleSuccessResponse(HttpURLConnection connection) throws IOException {
        InputStream response = connection.getErrorStream();
        if (response != null) {
            String message = new String(IOUtils.readFully(response, -1, true));
            System.out.println(message);
        } else {
            System.out.println("FAILED");
        }
    }
}
