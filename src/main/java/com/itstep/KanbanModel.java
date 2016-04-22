/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itstep;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Liona
 */
@Entity
@Table (name = "tasks")
public class KanbanModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "name", unique = true)
    private String name;
    @NotNull
    private String stateStr;
    @NotNull
    private String text;
    private String userName;
    private String endDate;
    
    
    public KanbanModel() {  }
    public KanbanModel(String name, String text) {
        this.stateStr = "PENDING";
        this.name = name;
        this.text = text;
    } 
    public KanbanModel(String name, String text, String userName) {
        this.stateStr = "PENDING";
        this.name = name;
        this.text = text;
        this.userName = userName;
    }
    public KanbanModel(String name, String text, String userName, String endDate){
        this.stateStr = "PENDING";
        this.name = name;
        this.text = text;
        this.userName = userName;
        this.endDate = endDate;
    }
    

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
  
    public String getStateStr() { return stateStr; }
    public void setStateStr(String stateStr) { this.stateStr = stateStr; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

}
