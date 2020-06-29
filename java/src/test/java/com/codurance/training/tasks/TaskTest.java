package com.codurance.training.tasks;

import org.junit.Test;
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
    
}
