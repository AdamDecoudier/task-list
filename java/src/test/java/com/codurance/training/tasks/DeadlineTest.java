package com.codurance.training.tasks;

import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void getIdTest(){
        Deadline deadline = new Deadline();
        deadline.setId(1L);
        assertEquals(1L, deadline.getId());
    }

    @Test
    public void getDateTest(){
    }
}
