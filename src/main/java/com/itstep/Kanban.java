package org.itstep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum State { PENDING, DONE, UNDONE }

/**
 *
 * @author Vadim
 */

public class Kanban {
    private static Integer idCounter = 0;
    private State state;
    private Integer id;
    private String text;
    private String name;
    private Boolean deleteMark;
    private User user;

    private LocalDateTime dateTimeEnd = LocalDateTime.now().plusDays(1);
    private DateTimeFormatter dateTimeEndFormater = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public static Integer getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Integer idCounter) {
        Kanban.idCounter = idCounter;
    }

    public Boolean getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Boolean deleteMark) {
        this.deleteMark = deleteMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setState(State state) {
        this.state = state;
    }

    public void setText(String text) {
        this.text = text;
    }

    public State getState() {
        return state;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Kanban(String name, String text, User user) {
        this.id = idCounter;
        this.idCounter++;
        this.state = State.PENDING;
        this.name = name;
        this.text = text;
        this.deleteMark = false;
        this.user = user;
    }
    public Kanban(String name, String text) {
        this.id = idCounter;
        this.idCounter++;
        this.state = State.PENDING;
        this.name = name;
        this.text = text;
        this.deleteMark = false;
        this.user = new User("NoName");
    }

    public Kanban(String text) {
        this.id = idCounter;
        this.idCounter++;
        this.state = State.PENDING;
        this.name = text;
        this.text = text;
        this.deleteMark = false;
        this.user = new User("NoName");
    }

    public Kanban(String name, String text, User user, String dateFormatEnd){
        this.id = idCounter;
        idCounter++;
        this.state = State.PENDING;
        this.name = name;
        this.text = text;
        this.deleteMark = false;
        this.user = user;
        try { this.dateTimeEnd = LocalDateTime.parse(dateFormatEnd, dateTimeEndFormater); }
        catch (Exception e) { this.dateTimeEnd = LocalDateTime.now().plusDays(1); }
    }
    public LocalDateTime getDateTimeEnd() { return dateTimeEnd; }

    public void setDateTimeEnd(String dateFormatEnd) { this.dateTimeEnd = LocalDateTime.parse(dateFormatEnd, dateTimeEndFormater); }
}