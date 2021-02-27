package mainClasses;

import org.openqa.selenium.InvalidArgumentException;

public enum Gender {
    MALE("мужчина"),
    FEMALE("женщина");
    private String ru;

    Gender(String ru) {
        this.ru = ru;
    }

    public String getRu() {
        return ru;
    }

    public static Gender parse(String g) {
        if (g.equals("m"))
            return MALE;
        if (g.equals("f"))
            return FEMALE;
        throw new InvalidArgumentException("Unknown gender value " + g);
    }

    public String toValue() {
        if (this == MALE)
            return "m";
        if (this == FEMALE)
            return "f";
        return null;
    }


}
