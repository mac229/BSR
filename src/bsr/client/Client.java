package bsr.client;

import bsr.Constants;
import bsr.server.Person;
import bsr.server.PersonService;

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
    private PersonService personService;

    public static Client getInstance() {
        return client;
    }

    public void init() {

        try {
            URL wsdlURL = new URL(Constants.PERSON_ENDPOINT_WSDL);
            QName qname = new QName(Constants.NAMESPACE_URI, Constants.PERSON_SERVICE_NAME);
            Service service = Service.create(wsdlURL, qname);
            personService = service.getPort(PersonService.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        //delete person
        System.out.println("Delete Person Status=" + personService.deletePerson(2));
    }

    public void get() {
        //get person
        System.out.println(personService.getPerson(1));
    }

    public void getAll() {
        //get all persons
        System.out.println(Arrays.asList(personService.getAllPersons()));
    }

    public void add() {
        Person p1 = new Person(1, "Pankaj", 30);
        Person p2 = new Person(2, "Meghna", 25);

        //add person
        System.out.println("Add Person Status=" + personService.addPerson(p1));
        System.out.println("Add Person Status=" + personService.addPerson(p2));
    }

}
