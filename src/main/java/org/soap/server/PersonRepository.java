package org.soap.server;

import api.soap.xsd.Person;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class PersonRepository {
    private static final Map<String, Person> persons = new HashMap<>();
    @PostConstruct
    public void initData(){
        Person johnJohnson = new Person();
        johnJohnson.setEMail("jj@mail.ma");
        johnJohnson.setFirstName("John");
        johnJohnson.setLastName("Johnson");
        johnJohnson.setRegNum(1010101);
        johnJohnson.setLicenseNum("AA0001AA");
        johnJohnson.setDate("01.01.1980");

        persons.put(johnJohnson.getEMail(), johnJohnson);

        Person peterPeterson = new Person();
        peterPeterson.setEMail("pp@mail.ma");
        peterPeterson.setFirstName("Peter");
        peterPeterson.setLastName("Peterson");
        peterPeterson.setRegNum(2020202);
        peterPeterson.setLicenseNum("AA0002AA");
        peterPeterson.setDate("02.02.1981");

        persons.put(peterPeterson.getEMail(), peterPeterson);

        Person stevenStevenson = new Person();
        stevenStevenson.setEMail("ss@mail.ma");
        stevenStevenson.setFirstName("Steven");
        stevenStevenson.setLastName("Stevenson");
        stevenStevenson.setRegNum(3030303);
        stevenStevenson.setLicenseNum("AA0003AA");
        stevenStevenson.setDate("03.03.1983");

        persons.put(stevenStevenson.getEMail(), stevenStevenson);

    }

    public Person findPerson(String eMail) {
        Assert.notNull(eMail, "The person's e-mail must not be null");
        return persons.get(eMail);
    }
}

