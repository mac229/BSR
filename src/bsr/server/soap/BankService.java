package bsr.server.soap;

import bsr.server.exception.TooSmallBalance;
import bsr.server.model.Account;
import bsr.server.model.Bill;
import bsr.server.model.Payment;
import bsr.server.model.Transfer;

import javax.jws.WebService;
import java.util.*;

/**
 * Created by Maciej on 2016-12-27.
 */
@WebService(endpointInterface = "bsr.server.soap.IBankService")
public class BankService implements IBankService {

    private static Map<Long, Account> accounts = new HashMap<>();
    private static List<Bill> bills = new ArrayList<>();

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
    public boolean login(String login, String password) {
        for (Account account : accounts.values()) {
            if (login.equals(account.getLogin()) && password.equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Bill> getBiils(long accountId) {
        return null;
    }

    @Override
    public double paymentIn(Payment payment) {
        for (Bill bill : bills) {
            if (bill.get)
        }
        return 0;
    }

    @Override
    public double paymentOut(Payment payment) throws TooSmallBalance {
        return 0;
    }

    @Override
    public double transfer(Transfer transfer) throws TooSmallBalance {
        return 0;
    }
}
