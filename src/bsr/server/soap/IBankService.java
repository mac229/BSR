package bsr.server.soap;

import bsr.server.exception.NotFound;
import bsr.server.exception.TooSmallBalance;
import bsr.server.model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;
import java.io.IOException;

/**
 * Created by Maciej on 2016-12-27.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface IBankService {

    @WebMethod
    boolean addAccount(Account account);

    @WebMethod
    Account getAccount(String login) throws NotFound;

    @WebMethod
    boolean login(String login, String password);

    @WebMethod
    Bill[] getBills(String login) throws NotFound;

    @WebMethod
    Bill getBill(String billNumber) throws NotFound;

    void createBill(String login) throws NotFound;

    @WebMethod
    double paymentIn(Payment payment) throws NotFound;

    @WebMethod
    double paymentOut(Payment payment) throws TooSmallBalance, NotFound;

    @WebMethod
    void transfer(Transfer transfer) throws TooSmallBalance, NotFound, IOException;

    @WebMethod
    Transaction[] getHistoryTransfers(String billNumber) throws NotFound;

    void closeSession();
}
