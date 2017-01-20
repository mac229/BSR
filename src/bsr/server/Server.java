package bsr.server;

import bsr.Constants;
import bsr.server.model.Config;
import bsr.server.soap.BankService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Maciej on 2016-12-27.
 */
public class Server {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Endpoint.publish(Constants.BANK_ENPDOINT, new BankService());

        URI baseUri = UriBuilder.fromUri(Constants.EXTERNAL).port(Constants.REST_PORT).build();
        MyResourceConfig config = new MyResourceConfig();
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        httpServer.start();

        System.out.println(Utils.getConfig().getAddresses().get("00109782"));
    }
}