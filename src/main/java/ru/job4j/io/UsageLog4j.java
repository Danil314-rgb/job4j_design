package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Danil";
        int age = 12;
        float weight = 55.5F;
        double height = 170.5;
        boolean children = false;
        byte eyes = 2;
        char classLetter = 'B';
        short classNumber = 5;
        LOG.debug("User info name : {}, age : {}, classLetter : {}, classNumber : {}, weight : {}, height : {}, children : {}, eyes : {}",
                name, age, classLetter, classNumber, weight, height, children, eyes);
    }
}
