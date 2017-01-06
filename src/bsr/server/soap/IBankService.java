package bsr.server.soap;

import bsr.server.exception.TooSmallBalance;
import bsr.server.model.Account;
import bsr.server.model.Bill;
import bsr.server.model.Payment;
import bsr.server.model.Transfer;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;
import java.util.ArrayList;
import java.util.List;

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
    public boolean deleteAccount(long id);

    @WebMethod
    public Account getAccount(long id);

    @WebMethod
    public Account[] getAccounts();

    @WebMethod
    public boolean login(String login, String password);

    @WebMethod
    public ArrayList<Bill> getBiils(long accountId);

    @WebMethod
    public double paymentIn(Payment payment);

    @WebMethod
    public double paymentOut(Payment payment) throws TooSmallBalance;

    @WebMethod
    public double transfer(Transfer transfer) throws TooSmallBalance;
}
