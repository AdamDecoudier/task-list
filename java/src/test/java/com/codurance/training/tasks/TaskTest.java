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

        Date date = new Date(2020,06,29);
        Task task2 = new Task(1L, "test", true, new Deadline(1L, date));
        assertEquals(1L, task2.getId());
        assertEquals("test", task2.getDescription());
        assertEquals(true, task2.isDone());
        assertEquals(new Deadline(1L, date).getId(), task2.getDeadline().getId());
        assertEquals(new Deadline(1L, date).getDate(), task2.getDeadline().getDate());
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
