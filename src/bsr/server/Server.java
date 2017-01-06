package bsr.server;

import bsr.Constants;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Maciej on 2016-12-27.
 */
public class Server {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Endpoint.publish(Constants.PERSON_ENPDOINT, new PersonServiceImpl());
        System.out.println("Started");
    }
}