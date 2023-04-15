package com.example.myfirstapp.model;

import java.sql.Timestamp;
import java.util.Date;

public class Tasks {
    private int taskid;
    private String task;
    private int userid;
    private Timestamp datecreated;
    private String description;

    public Tasks() {

    }

    public Tasks(String task, int userid, Timestamp datecreated, String description) {
        this.task = task;
        this.userid = userid;
        this.datecreated = datecreated;
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }
}
