package org.soap.server;

import api.soap.xsd.GetPersonRequest;
import api.soap.xsd.GetPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PersonEndPoint {
    private static final String NAMESPACE_URI = "http://soap.api/xsd";

    private PersonRepository personRepository;

    @Autowired
    public PersonEndPoint(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setPerson(personRepository.findPerson(request.getEMail()));

        return response;
    }
}

