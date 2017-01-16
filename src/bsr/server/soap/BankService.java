package bsr.server.soap;

import bsr.Constants;
import bsr.server.exception.NotFound;
import bsr.server.exception.TooSmallBalance;
import bsr.server.model.*;
import bsr.server.rest.TransferService;

import javax.jws.WebService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Maciej on 2016-12-27.
 */
@WebService(endpointInterface = "bsr.server.soap.IBankService")
public class BankService implements IBankService {

    private static Map<Long, Account> accounts = new HashMap<>();
    private static ArrayList<Bill> bills = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public BankService() {
        Account account = new Account(1, "admin", "admin");
        Bill bill = new Bill("99001097820000000000000001", 1000);
        account.addBill(bill);
        bills.add(bill);
        addAccount(account);
    }

    @Override
    public boolean addAccount(Account account) {
        if (accounts.get(account.getId()) != null) {
            return false;
        }

        accounts.put(account.getId(), account);
        return true;
    }

    @Override
    public boolean deleteAccount(long id) {
        if (accounts.get(id) == null) {
            return false;
        }

        accounts.remove(id);
        return true;
    }

    @Override
    public Account getAccount(long id) {
        return accounts.get(id);
    }

    @Override
    public Account[] getAccounts() {
        Set<Long> ids = accounts.keySet();
        Account[] result = new Account[ids.size()];
        int i = 0;
        for (Long id : ids) {
            result[i] = accounts.get(id);
            i++;
        }
        return result;
    }

    @Override
    public long login(String login, String password) {
        for (Account account : accounts.values()) {
            if (login.equals(account.getLogin()) && password.equals(account.getPassword())) {
                return account.getId();
            }
        }
        return Constants.UNDEFINED;
    }

    @Override
    public Bill[] getBills(long accountId) {
        ArrayList<Bill> bills = new ArrayList<>();
        bills.add(new Bill("99001097820000000000000001", 1000));
        Bill[] result = new Bill[bills.size()];
        return bills.toArray(result);
    }

    @Override
    public Bill getBill(String billNumber) throws NotFound {
        for (Bill bill : bills) {
            if (bill.getNumber().equals(billNumber)) {
                return bill;
            }
        }

        throw new NotFound();
    }

    @Override
    public double paymentIn(Payment payment) throws NotFound {
        for (Bill bill : bills) {
            if (bill.getNumber().equals(payment.getBillNumber())) {
                double newBalance = bill.getBalance() + payment.getAmount();
                bill.setBalance(newBalance);
                return newBalance;
            }
        }

        throw new NotFound();
    }

    @Override
    public double paymentOut(Payment payment) throws TooSmallBalance, NotFound {
        for (Bill bill : bills) {
            if (bill.getNumber().equals(payment.getBillNumber())) {
                double balance = bill.getBalance();
                if (balance - payment.getAmount() < 0) {
                    throw new TooSmallBalance();
                } else {
                    double newBalance = bill.getBalance() - payment.getAmount();
                    bill.setBalance(newBalance);
                    return newBalance;
                }
            }
        }

        throw new NotFound();
    }

    @Override
    public double transfer(Transfer transfer) throws TooSmallBalance, NotFound {
        System.out.println(transfer);
        try {
            new TransferService().transfer(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Transaction[] getHistoryTransfers(String billNumber) throws NotFound {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getBillNumber().equals(billNumber)) {
                result.add(transaction);
            }
        }

        Transaction[] array = new Transaction[result.size()];
        return result.toArray(array);
    }
}
