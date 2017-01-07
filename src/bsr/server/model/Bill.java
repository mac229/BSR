package bsr.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Bill {

    private String number;
    private double balance;
    private List<HistoryTransfer> historyTransfers = new ArrayList<>();

    public Bill() {
    }

    public Bill(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<HistoryTransfer> getHistoryTransfers() {
        return historyTransfers;
    }

    public void setHistoryTransfers(List<HistoryTransfer> historyTransfers) {
        this.historyTransfers = historyTransfers;
    }

    public void addHistoryTransfers(HistoryTransfer historyTransfer) {
        historyTransfers.add(historyTransfer);
    }
}
