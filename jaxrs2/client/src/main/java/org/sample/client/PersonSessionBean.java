/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.client;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author arungup
 */
@Singleton
public class PersonSessionBean {
    List<Person> list;
    
    public PersonSessionBean() {
        System.out.println("ctor");
        list = new ArrayList<>();
    }
    
    public void addPerson(Person p) {
        list.add(p);
    }
    
    public void deletePerson(String name) {
        for (Person p : list) {
            if (p.getName().equals(name)) {
                list.remove(p);
            }
        }
    }
    
    public List<Person> getPersons() {
        return list;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
