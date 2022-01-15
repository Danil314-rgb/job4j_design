package ru.job4j.io.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = " ")
public class Number {

    @XmlAttribute
    private String number;

    public Number() {
    }

    public Number(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" + "number='" + number + '\'' + '}';
    }
}
