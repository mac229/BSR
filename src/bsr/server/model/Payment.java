package bsr.server.model;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Payment {

    private double amount;
    private String billNumber;

    public Payment() {

    }

    public Payment(double amount, String billNumber) {
        this.amount = amount;
        this.billNumber = billNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
}
