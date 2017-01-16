package bsr.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Bill {

    private String number;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Bill() {
    }

    public Bill(String number) {
        this.number = number;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addHistoryTransfers(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (number != null ? !number.equals(bill.number) : bill.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    @Override
    public String toString() {
        return number;
    }
}
