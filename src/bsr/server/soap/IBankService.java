package bsr.server.soap;

import bsr.server.model.Account;

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
    public boolean deleteAccount(long id);

    @WebMethod
    public Account getAccount(long id);

    @WebMethod
    public Account[] getAccounts();
}
