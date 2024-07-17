package org.example.models.dao;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    List<Person> people = new ArrayList<>();
    static int COUNTER_PEOPLE = 0;

    PersonDAO() {
        people.add(new Person(++COUNTER_PEOPLE, "Ilya"));
        people.add(new Person(++COUNTER_PEOPLE, "Semen"));
        people.add(new Person(++COUNTER_PEOPLE, "Elena"));
        people.add(new Person(++COUNTER_PEOPLE, "Evgenia"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(int index){
        return people.stream().filter(person -> person.getId() == index).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++COUNTER_PEOPLE);
        this.people.add(person);
    }

    public void edit(int index, Person person){
        person.setId(index);
        Optional<Person> personToReplace = people.stream()
                .filter(p -> p.getId() == index)
                .findFirst();

        personToReplace.ifPresent(p -> {
            people.set(people.indexOf(p), person);
        });
    }

    public void edit(int index, String name){
        people.get(index).setName(name);
    }

    public void delete(int index){
        people.removeIf(p->p.getId() == index);
    }
}
