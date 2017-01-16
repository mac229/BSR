package bsr.server.model;

/**
 * Created by Maciej on 2017-01-16.
 */
public class Config {

    private String credentials;
    private String externalBankId;
    private String externalBankAddress;

    public String getExternalBankId() {
        return externalBankId;
    }

    public String getExternalBankAddress() {
        return externalBankAddress;
    }

    public String getCredentials() {
        return credentials;
    }
}
