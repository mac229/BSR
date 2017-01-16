package bsr.server.soap;

import bsr.Constants;
import bsr.server.Data;
import bsr.server.Utils;
import bsr.server.exception.NotFound;
import bsr.server.exception.TooSmallBalance;
import bsr.server.model.*;
import bsr.server.rest.TransferService;

import javax.jws.WebService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2016-12-27.
 */
@WebService(endpointInterface = "bsr.server.soap.IBankService")
public class BankService implements IBankService {

    @Override
    public boolean addAccount(Account account) {
        Data.getInstance().addAccount(account);
        return true;
    }

    @Override
    public Account getAccount(long id) throws NotFound {
        for (Account account : Data.getInstance().getAccounts()) {
            if (account.getId() == id) {
                return account;
            }
        }

        throw new NotFound();
    }

    @Override
    public Account[] getAccounts() {
        List<Account> accounts = Data.getInstance().getAccounts();
        Account[] array = new Account[accounts.size()];
        return accounts.toArray(array);
    }

    @Override
    public long login(String login, String password) {
        for (Account account : Data.getInstance().getAccounts()) {
            if (login.equals(account.getLogin()) && password.equals(account.getPassword())) {
                return account.getId();
            }
        }
        return Constants.UNDEFINED;
    }

    @Override
    public Bill[] getBills(long accountId) throws NotFound {
        for (Account account : Data.getInstance().getAccounts()) {
            if (account.getId() == accountId) {
                List<Bill> bills = account.getBills();
                Bill[] result = new Bill[bills.size()];
                return bills.toArray(result);
            }
        }

        throw new NotFound();
    }

    @Override
    public Bill getBill(String billNumber) throws NotFound {
        for (Account account : Data.getInstance().getAccounts()) {
            for (Bill bill : account.getBills()) {
                if (bill.getNumber().equals(billNumber)) {
                    return bill;
                }
            }
        }

        throw new NotFound();
    }

    @Override
    public double paymentIn(Payment payment) throws NotFound {
        Bill bill = getBill(payment.getBillNumber());
        double newBalance = bill.getBalance() + payment.getAmount();
        bill.setBalance(newBalance);
        return newBalance;
    }

    @Override
    public double paymentOut(Payment payment) throws TooSmallBalance, NotFound {
        Bill bill = getBill(payment.getBillNumber());
        double balance = bill.getBalance();
        if (balance - payment.getAmount() < 0) {
            throw new TooSmallBalance();
        } else {
            double newBalance = bill.getBalance() - payment.getAmount();
            bill.setBalance(newBalance);
            return newBalance;
        }
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
        for (Transaction transaction : Data.getInstance().getTransactions()) {
            if (transaction.getBillNumber().equals(billNumber)) {
                result.add(transaction);
            }
        }

        Transaction[] array = new Transaction[result.size()];
        return result.toArray(array);
    }

    @Override
    public void closeSession() {
        Data data = Data.getInstance();
        Utils.save(data);
    }
}
