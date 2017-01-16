package bsr.server.soap;

import bsr.server.exception.NotFound;
import bsr.server.exception.TooSmallBalance;
import bsr.server.model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

/**
 * Created by Maciej on 2016-12-27.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface IBankService {

    @WebMethod
    public boolean addAccount(Account account);

    @WebMethod
    public boolean deleteAccount(long id) throws NotFound;

    @WebMethod
    public Account getAccount(long id);

    @WebMethod
    public Account[] getAccounts();

    @WebMethod
    public long login(String login, String password);

    @WebMethod
    public Bill[] getBills(long accountId);

    @WebMethod
    public Bill getBill(String billNumber) throws NotFound;

    @WebMethod
    public double paymentIn(Payment payment) throws NotFound;

    @WebMethod
    public double paymentOut(Payment payment) throws TooSmallBalance, NotFound;

    @WebMethod
    public double transfer(Transfer transfer) throws TooSmallBalance, NotFound;

    @WebMethod
    public Transaction[] getHistoryTransfers(String billNumber) throws NotFound;
}
