package org.itstep;

import java.util.ArrayList;

/*
 * @author Vadim
 */
public class User {

    private long id;
    private String name;
    private ArrayList<Integer> tasks;

    public void addTask(int taskId) {
        this.tasks.add(taskId);
    }
    public ArrayList<Integer> getTasks(){
        return tasks;
    }
    
    public User(String name){
        this.name = name;
    }
    
    public User(long id, String name){
        this.id = id;
        this.name = name;
        tasks = new ArrayList<>();
    }
    
    
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}


