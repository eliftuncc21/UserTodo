package model;
import java.io.ObjectInputFilter;
import java.time.LocalDateTime;

public class Todo extends BaseEntity  {
    private String title;
    private String description;
    private int userId;
    private Status status;
    private LocalDateTime registrCreate ;

    public Todo(int id, String title, String description, int userId, Status status, LocalDateTime registrCreate ){
        setId(id);
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.status = status;
        this.registrCreate  = registrCreate ;
    }
    public Todo() {
    }

    public Todo(int id, String description, int userId, ObjectInputFilter.Status value, String registrCreate) {
        super();
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

    public LocalDateTime getRegistrCreate() {
        return registrCreate ;
    }

    public void setRegistrCreate(LocalDateTime registrCreate ) {
        this.registrCreate  = registrCreate ;
    }
}
