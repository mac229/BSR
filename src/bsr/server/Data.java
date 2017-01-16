package bsr.server;

import bsr.server.model.Account;
import bsr.server.model.Bill;
import bsr.server.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Data {

    private static Data instance = Utils.getData();

    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public static Data getInstance() {
        return instance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
