package com.codurance.training.tasks;

import java.util.Date;

public class Deadline {

    private Long id;

    private Date date;

    public Deadline(){
    }

    public Deadline(Long id, Date date){
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}