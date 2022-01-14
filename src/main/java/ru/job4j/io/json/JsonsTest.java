package ru.job4j.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonsTest {

    public static void main(String[] args) {

        final Phone phone = new Phone(true, (byte) 32, "pixel",
                new Number("11-111"), new String[]{"Tom", "Bob"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(phone));

        final String personJson =
                "{"
                        + "\"camera\":true,"
                        + "\"megapixels\":34,"
                        + "\"name\": Pixel,"
                        + "\"number\":"
                        + "{"
                        + "\"number\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"contacts\":"
                        + "[\"Bob\",\"Tom\"]"
                        + "}";
        final Phone personMod = gson.fromJson(personJson, Phone.class);
        System.out.println(personMod);
    }
}
