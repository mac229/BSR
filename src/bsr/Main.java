package bsr;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Maciej on 2016-12-27.
 */
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println("Hello");
        Endpoint.publish("http://localhost:8888/ws/person", new PersonServiceImpl());
        System.out.println("Hello");
    }
}