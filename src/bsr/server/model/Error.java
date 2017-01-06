package bsr.server.model;


import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Error {

    @XmlElement(name = "amount")
    private String error;

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
