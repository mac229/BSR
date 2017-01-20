package bsr.server.model;

import java.util.Map;

/**
 * Created by Maciej on 2017-01-16.
 */
public class Config {

    private String credentials;
    private Map<String, String> addresses;

    public String getCredentials() {
        return credentials;
    }

    public Map<String, String> getAddresses() {
        return addresses;
    }
}
