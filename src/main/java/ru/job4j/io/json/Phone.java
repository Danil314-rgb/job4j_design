package ru.job4j.io.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "phone")
@XmlAccessorType(XmlAccessType.FIELD)
public class Phone {

    @XmlAttribute
    private boolean camera;

    @XmlAttribute
    private byte megapixels;

    @XmlAttribute
    private String name;

    @XmlElement
    private Number number;

    @XmlElement
    private String[] contacts;

    public Phone() {
    }

    public Phone(boolean camera, byte megapixels, String name, Number number, String[] contacts) {
        this.camera = camera;
        this.megapixels = megapixels;
        this.name = name;
        this.number = number;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Phone{" + "camera=" + camera + ", megapixels=" + megapixels + ", name='" + name + '\'' + ", number=" + number + ", contacts=" + Arrays.toString(contacts) + '}';
    }
}
