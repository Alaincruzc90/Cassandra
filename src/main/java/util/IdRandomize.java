package util;

import org.apache.commons.lang3.RandomStringUtils;

public class IdRandomize {

    private IdRandomize() {}

    public static String getRandomId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!*()-_=+,.";
        return RandomStringUtils.random(15, characters);
    }
}

