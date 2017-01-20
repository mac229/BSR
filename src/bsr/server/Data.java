package bsr.server;

import bsr.Constants;
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
    private long billsCount;

    public static Data getInstance() {
        return instance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean addAccount(Account account) {
        if (accounts.contains(account)) {
            return false;
        } else {
            Bill bill = createBill();
            account.addBill(bill);
            accounts.add(account);
            return true;
        }
    }

    public Bill createBill() {
        ++billsCount;
        long billNumber = billsCount * 10_000 + 2521;
        long checksum = billNumber % 97;
        String number = "";
        if (checksum < 10) {
            number += 0;
        }
        number += checksum + Constants.BANK_ID + rest(billsCount);

        return new Bill(number);
    }

    private String rest(long billsCount) {
        String string = String.valueOf(billsCount);
        StringBuilder stringBuilder = new StringBuilder(16);
        for (int i = string.length(); i < 16; i++) {
            stringBuilder.append("0");
        }
        stringBuilder.append(billsCount);
        return stringBuilder.toString();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
