package bsr;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Maciej on 2016-12-27.
 */
@WebService(endpointInterface = "bsr.PersonService")
public class PersonServiceImpl implements PersonService {

    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

    @Override
    public boolean addPerson(Person p) {
        if (persons.get(p.getId()) != null) return false;
        persons.put(p.getId(), p);
        return true;
    }

    @Override
    public boolean deletePerson(int id) {
        if (persons.get(id) == null) return false;
        persons.remove(id);
        return true;
    }

    @Override
    public Person getPerson(int id) {
        return persons.get(id);
    }

    @Override
    public Person[] getAllPersons() {
        Set<Integer> ids = persons.keySet();
        Person[] people = new Person[ids.size()];
        int i = 0;
        for (Integer id : ids) {
            people[i] = persons.get(id);
            i++;
        }
        return people;
    }

}
