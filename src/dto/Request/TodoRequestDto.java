package dto.Request;

import model.Status;

public class TodoRequestDto {
    private String title;
    private String description;
    private int userId;
    private Status status;

    public TodoRequestDto(String title, String description, int userId, Status status) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.status = status;
    }

    public TodoRequestDto() {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public Status getStatus() {

        return status;
    }

    public void setStatus(Status status) {

        this.status = status;
    }
}
