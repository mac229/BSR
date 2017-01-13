package bsr.server.rest;

import bsr.Constants;
import bsr.server.model.Transfer;
import com.google.gson.Gson;
import org.glassfish.jersey.internal.util.Base64;
import sun.misc.IOUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    // This method is called if XMLis request
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer getTransfer() {
        return new Transfer("title", 100, "sender", "receiver");
    }

    public void transfer(Transfer transfer) throws IOException {
        String url = Constants.EXTERNAL_BANK_ADDRESS + "/transfer";

        String data = new Gson().toJson(transfer);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", Constants.UTF_8);
        connection.setRequestProperty("Content-Type", "application/json;charset=" + Constants.UTF_8);
        connection.setRequestProperty("Authorization", "Basic " + Base64.encodeAsString(Constants.BANK_CREDENTIALS));

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
