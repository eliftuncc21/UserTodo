package dto.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserResponseDto {
    private String fullName;
    private String name;
    private String surname;
    private int age;
    private LocalDateTime registrationDate;

    public UserResponseDto(String name, String surname, int age, LocalDateTime registrationDate) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.registrationDate = registrationDate;
    }

    public UserResponseDto() {
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
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

    public LocalDateTime getRegistrationDate(){
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate){
        this.registrationDate = registrationDate;
    }

    public String getFormattedRegistrationDate() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return registrationDate != null ? registrationDate.format(myFormatObj) : "Tarih yok";
    }

    public String toString(){
        return "Name - Surname: " + fullName
                + " Age: " + age
                + " Registered: " + getFormattedRegistrationDate();
    }
}
