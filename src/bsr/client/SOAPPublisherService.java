package bsr.client;

import bsr.Person;
import bsr.PersonService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by Maciej on 2016-12-27.
 */
public class SOAPPublisherService {

    public static void main(String[] args) throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:8888/ws/person?wsdl");
        //check above URL in browser, you should see WSDL file

        //creating QName using targetNamespace and name
        QName qname = new QName("http://bsr/", "PersonServiceImplService");

        Service service = Service.create(wsdlURL, qname);

        //We need to pass interface and model beans to client
        PersonService personService = service.getPort(PersonService.class);

        Person p1 = new Person();
        p1.setName("Pankaj");
        p1.setId(1);
        p1.setAge(30);
        Person p2 = new Person();
        p2.setName("Meghna");
        p2.setId(2);
        p2.setAge(25);

        //add person
        System.out.println("Add Person Status=" + personService.addPerson(p1));
        System.out.println("Add Person Status=" + personService.addPerson(p2));

        //get person
        System.out.println(personService.getPerson(1));

        //get all persons
        System.out.println(Arrays.asList(personService.getAllPersons()));

        //delete person
        System.out.println("Delete Person Status=" + personService.deletePerson(2));

        //get all persons
        System.out.println(Arrays.asList(personService.getAllPersons()));

    }

}
