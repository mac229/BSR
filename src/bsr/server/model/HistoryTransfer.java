package bsr.server.model;

/**
 * Created by Maciej on 2017-01-06.
 */
public class HistoryTransfer {

    private String title;
    private double amount;
    private String type;
    private double balance;
    private String billNumber;

    public HistoryTransfer() {
    }

    public HistoryTransfer(String title, double amount, String type, double balance, String billNumber) {
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.balance = balance;
        this.billNumber = billNumber;
    }

    public HistoryTransfer(String title, double amount, String type, double balance, String billNumber, String from) {
        this.title = title;
        this.amount = amount;
        this.type = type + " od " + from;
        this.balance = balance;
        this.billNumber = billNumber;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "title= " + title +
                ", amount= " + amount +
                ", type= " + type +
                ", balance= " + balance;
    }

    public String getBillNumber() {
        return billNumber;
    }
}
