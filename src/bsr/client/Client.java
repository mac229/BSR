package bsr.client;

import bsr.Constants;
import bsr.server.model.Account;
import bsr.server.soap.IBankService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by Maciej on 2016-12-27.
 */
public class Client {

    private static Client client = new Client();
    private IBankService bankService;

    public static Client getInstance() {
        return client;
    }

    public void init() {

        try {
            URL wsdlURL = new URL(Constants.BANK_ENDPOINT_WSDL);
            QName qname = new QName(Constants.NAMESPACE_URI, Constants.PERSON_SERVICE_NAME);
            Service service = Service.create(wsdlURL, qname);
            bankService = service.getPort(IBankService.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        System.out.println("Delete Person Status=" + bankService.deleteAccount(1));
    }

    public void get() {
        System.out.println(bankService.getAccount(1));
    }

    public void getAll() {
        System.out.println(Arrays.asList(bankService.getAccounts()));
    }

    public void add() {
        Account account = new Account(1, "admin", "admin");
        System.out.println("Add account status=" + bankService.addAccount(account));
    }
}
