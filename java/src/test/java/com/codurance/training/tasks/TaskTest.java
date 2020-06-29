package com.codurance.training.tasks;

import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void constructorTest(){
        Task task = new Task(1L, "test", true);
        assertEquals(1L, task.getId());
        assertEquals("test", task.getDescription());
        assertEquals(true, task.isDone());
    }

    @Test
    public void getIdTest(){
        Task task = new Task(1L, "test", true);
        assertEquals(1L, task.getId());
    }

    @Test
    public void getDescriptionTest(){
        Task task = new Task(1L, "test", true);
        assertEquals("test", task.getDescription());
    }

    @Test
    public void isDoneTest(){
        Task task = new Task(1L, "test", true);
        assertEquals(true, task.isDone());
    }

    @Test
    public void setDoneTest(){
        Task task = new Task(1L, "test", true);
        task.setDone(false);
        assertEquals(false, task.isDone());
    }

    @Test
    public void getDeadline(){
        Task task = new Task(1L, "test", true);
        task.setDeadline(new Deadline(1L, new Date(2020, 06, 29 )));
    }
}
