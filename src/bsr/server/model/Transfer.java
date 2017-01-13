package bsr.server.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Maciej on 2017-01-06.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transfer {

    @XmlElement(name = "title")
    @SerializedName("title")
    private String title;

    @XmlElement(name = "amount")
    @SerializedName("amount")
    private double amount;

    @XmlElement(name = "sender_account")
    @SerializedName("sender_account")
    private String sender;

    @XmlElement(name = "receiver_account")
    @SerializedName("receiver_account")
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

    @Override
    public String toString() {
        return "title=" + title +
                ", amount=" + amount +
                ", sender=" + sender +
                ", receiver=" + receiver;
    }
}
