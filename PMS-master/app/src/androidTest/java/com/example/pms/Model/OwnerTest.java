package com.example.pms.Model;

import static org.junit.Assert.assertEquals;

import com.example.pms.Exceptions.PasswordException;
import com.example.pms.Exceptions.UserNameException;
import com.example.pms.Model.Owner;

import org.junit.Before;
import org.junit.Test;

public class OwnerTest {
    Owner own;
    @Before
    public void setUp() {
        own = new Owner();
    }
    @Test
    public void setPassword_Test() {
        String expected = "123456789";
        own.setPassword(expected);
        assertEquals(own.getPassword(),expected);
    }



    @Test
    public void setUserName_Test() {
        String expected = "Ahmed Rohan";
        own.setUserName(expected);
        assertEquals(own.getUserName(),expected);
    }


}
