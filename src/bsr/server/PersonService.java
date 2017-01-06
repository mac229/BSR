package bsr.server; /**
 * Created by Maciej on 2016-12-27.
 */

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface PersonService {

    @WebMethod
    public boolean addPerson(Person p);

    @WebMethod
    public boolean deletePerson(int id);

    @WebMethod
    public Person getPerson(int id);

    @WebMethod
    public Person[] getAllPersons();
}
