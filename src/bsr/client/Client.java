package bsr.client;

import bsr.Constants;
import bsr.server.exception.NotFound;
import bsr.server.model.Account;
import bsr.server.model.Bill;
import bsr.server.model.HistoryTransfer;
import bsr.server.soap.IBankService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maciej on 2016-12-27.
 */
public class Client {

    private static Client client = new Client();
    private IBankService bankService;
    private ArrayList<Bill> bills;
    private Bill bill;

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

    public IBankService getBankService(){
        return bankService;
    }

    public void getBills(long accountId) {
        bankService.getBills(accountId);
    }

    public List<HistoryTransfer> getHistory() throws NotFound {
        HistoryTransfer[] historyTransfers = bankService.getHistoryTransfers(bill.getNumber());
        return Arrays.asList(historyTransfers);
    }
}
