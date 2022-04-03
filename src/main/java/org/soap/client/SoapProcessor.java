package org.soap.client;

import org.xml.sax.InputSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SoapProcessor {
    private HttpURLConnection connection;
    public SoapProcessor () {
        try {
            connection=connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HttpURLConnection connect() throws IOException {
        URL obj = new URL("http","localhost",8080,"/ws");
        return (HttpURLConnection) obj.openConnection();
    }

    public void request(String message) throws IOException {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","text/xml; charset=utf-8");
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "                  xmlns:gs=\"http://soap.api/xsd\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <gs:getPersonRequest>\n" +
                "            <gs:eMail>"+message+"</gs:eMail>\n" +
                "        </gs:getPersonRequest>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(xml);
        wr.flush();
        wr.close();
    }
    public void response() throws TransformerException, IOException {
        Transformer serializer= SAXTransformerFactory.newInstance().newTransformer();
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Source xmlSource=new SAXSource(new InputSource(new InputStreamReader(connection.getInputStream())));
        StreamResult res =  new StreamResult(new ByteArrayOutputStream());
        serializer.transform(xmlSource, res);
        System.out.println(res.getOutputStream().toString());
    }

}
