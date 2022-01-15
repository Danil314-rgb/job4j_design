package ru.job4j.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonsTest {

    public static void main(String[] args) throws Exception {
        Phone phone = new Phone(true, (byte) 32, "pixel",
                new Number("+7(924)111-111-11-11"), new String[]{"Tom", "Bob"});
        JAXBContext context = JAXBContext.newInstance(Phone.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(phone, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Phone result = (Phone) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
