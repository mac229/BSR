package bsr.server.soap;

import bsr.server.model.Account;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Maciej on 2016-12-27.
 */
@WebService(endpointInterface = "bsr.server.soap.IBankService")
public class BankService implements IBankService {

    private static Map<Long, Account> accounts = new HashMap<>();

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
}
