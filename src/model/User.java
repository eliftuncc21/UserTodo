package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class User  extends BaseEntity implements Serializable{
    private String name;
    private String surname;
    private int age;
    private LocalDateTime registrationDate;

    public User(int id, String name, String surname, int age, LocalDateTime registrationDate ) {
        setId(id);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.registrationDate = registrationDate;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String toString(){
        return "{id: " + getId() + ", name: " + name + ", surname: " + surname +
                ", age: " + age + ", registrationDate: " + registrationDate + "}";
    }
}