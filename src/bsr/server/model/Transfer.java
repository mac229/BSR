package bsr.server.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

/**
 * Created by Maciej on 2017-01-06.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transfer {

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "amount")
    private double amount;

    @XmlElement(name = "sender_account")
    private String sender;

    @XmlElement(name = "receiver_account")
    private String receiver;

    public Transfer() {
    }

    public Transfer(String title, double amount, String sender, String receiver) {
        this.title = title;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
