package com.itstep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum State { PENDING, DONE, UNDONE }

/**
 *
 * @author Vadim
 */

public class Kanban {
    
    private long id;
    private String name;
    private String text;
    private State state;
    private String user;
    
    private LocalDateTime dateTimeEnd = LocalDateTime.now().plusDays(1);
    private DateTimeFormatter dateTimeEndFormater = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    
    private Boolean deleteMark;

    
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public State getState() { return state; }
    public void setState(State state) { this.state = state; }
    
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    
    public Boolean getDeleteMark() { return deleteMark; }
    public void setDeleteMark(Boolean deleteMark) { this.deleteMark = deleteMark; }


    
    public Kanban(KanbanModel kModel){
        this.id = kModel.getId();
        this.name = kModel.getName();
        this.text = kModel.getText();
        this.state = State.valueOf(kModel.getStateStr());
        this.user = kModel.getUserName();
        try { this.dateTimeEnd = LocalDateTime.parse(kModel.getEndDate(), dateTimeEndFormater); }
        catch (Exception e) { this.dateTimeEnd = LocalDateTime.now().plusDays(1); }
    }

    public LocalDateTime getDateTimeEnd() { return dateTimeEnd; }
    public void setDateTimeEnd(String dateFormatEnd) { this.dateTimeEnd = LocalDateTime.parse(dateFormatEnd, dateTimeEndFormater); }
}