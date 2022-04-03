package org.soap.client;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        SoapProcessor sp = new SoapProcessor();
        try {
            sp.request("jj@mail.ma");
            sp.response();

        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
