package mainClasses;

import java.util.Objects;

public class Subscriber {
    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;

    //constructors


    public Subscriber() {

    }

    public Subscriber(String firstName, String lastName, Gender gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    //setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscriber)) return false;
        Subscriber that = (Subscriber) o;
        return id == that.id && age == that.age && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, age);
    }

    //displayed
    @Override
    public String toString() {
        return "\n\nSubscriber #" + id +
                "\n id = " + id +
                ",\n firstName = '" + firstName + '\'' +
                ",\n lastName = '" + lastName + '\'' +
                ",\n gender = " + gender +
                ",\n age = " + age;
    }
}
