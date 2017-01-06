package bsr.server.model;

/**
 * Created by Maciej on 2017-01-06.
 */
public class HistoryTransfer {

    private String title;
    private double amount;
    private String source;
    private double balance;

    public HistoryTransfer(String title, double amount, String source, double balance) {
        this.title = title;
        this.amount = amount;
        this.source = source;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "title= " + title +
                ", amount= " + amount +
                ", source= " + source +
                ", balance= " + balance;
    }
}
