package dto.Response;

import model.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoResponseDto {
    private String fullName;
    private String title;
    private String description;
    private int userId;
    private Status status;
    private LocalDateTime registrCreate;

    public TodoResponseDto(String fullName, String title, String description, int userId, Status status, LocalDateTime registrCreate){
        this.fullName = fullName;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.status = status;
        this.registrCreate = registrCreate;
    }

    public TodoResponseDto(){
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }
    public LocalDateTime getRegistrCreate(){
        return registrCreate ;
    }
    public void setRegistrCreate(LocalDateTime registrCreate ){
        this.registrCreate  = registrCreate ;
    }
    public String getFormattedRegistrationDate() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return registrCreate  != null ? registrCreate .format(myFormatObj) : "Tarih yok";
    }

    public String toString(){
        return "Name Surname: " + fullName
                + " Title: " + title
                + " Description: " + description
                + " Status: " + status
                + " Registered: " + getFormattedRegistrationDate();

    }
}
