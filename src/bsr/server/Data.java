package bsr.server;

import bsr.server.model.Account;
import bsr.server.model.Bill;

import java.util.List;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Data {

    private static Data instance = new Data();

    private List<Account> accounts;
    private List<Bill> bills;

    public static Data getInstance() {
        return instance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }
}
