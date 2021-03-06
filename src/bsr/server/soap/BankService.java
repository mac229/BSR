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
        return Data.getInstance().addAccount(account);
    }

    @Override
    public Account getAccount(String login) throws NotFound {
        for (Account account : Data.getInstance().getAccounts()) {
            if (account.getLogin().equals(login)) {
                return account;
            }
        }

        throw new NotFound();
    }

    @Override
    public boolean login(String login, String password) {
        for (Account account : Data.getInstance().getAccounts()) {
            if (login.equals(account.getLogin()) && password.equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Bill[] getBills(String login) throws NotFound {
        for (Account account : Data.getInstance().getAccounts()) {
            if (account.getLogin().equals(login)) {
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
    public void createBill(String login) throws NotFound {
        Bill bill = Data.getInstance().createBill();
        Account account = getAccount(login);
        account.addBill(bill);
    }

    @Override
    public double paymentIn(Payment payment) throws NotFound {
        Bill bill = getBill(payment.getBillNumber());
        double newBalance = bill.getBalance() + payment.getAmount();
        bill.setBalance(newBalance);
        Transaction transaction = new Transaction("Wpłata na konto", payment.getAmount(), "Wpłata", newBalance, payment.getBillNumber());
        Data.getInstance().addTransaction(transaction);
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
            Transaction transaction = new Transaction("Wypłata z konta", payment.getAmount(), "Wypłata", newBalance, payment.getBillNumber());
            Data.getInstance().addTransaction(transaction);
            return newBalance;
        }
    }

    @Override
    public void transfer(Transfer transfer) throws TooSmallBalance, NotFound, IOException {
        System.out.println(transfer);
        String bankId = transfer.getReceiver().substring(2,10);
        if (Constants.BANK_ID.equals(bankId)) {
            makeInternalTransfer(transfer);
        } else {
            makeExternalTransfer(transfer);
        }
    }

    private void makeInternalTransfer(Transfer transfer) throws NotFound, TooSmallBalance {
        double amount = (double) transfer.getAmount() / 100;

        Bill billSender = getBill(transfer.getSender());
        double balance = billSender.getBalance();
        if (balance - amount < 0) {
            throw new TooSmallBalance();
        }

        Bill billReceiver = getBill(transfer.getReceiver());
        double newBalance = billReceiver.getBalance() + amount;
        billReceiver.setBalance(newBalance);

        double newBalanceS = billSender.getBalance() - amount;
        billSender.setBalance(newBalanceS);
        Transaction transaction = new Transaction(transfer.getTitle(), amount, "Transfer", newBalanceS, transfer.getSender());

        Data.getInstance().addTransaction(transaction);
    }

    private void makeExternalTransfer(Transfer transfer) throws IOException {
        new TransferService().transfer(transfer);
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
