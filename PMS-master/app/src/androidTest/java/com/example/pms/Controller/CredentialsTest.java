package com.example.pms.Controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

public class CredentialsTest {

    Credentials credentials;
    String username;
    String password;
    Context context;


    @Before
    public void setUp() {

        credentials = Credentials.getInstance();
        username = "test123";
        password = "ab434433gtgtc";
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    }

    @Test
    public void signUp() {

        assertTrue(credentials.signUp(username, password, context));

    }

    @Test
    public void invalidSignup() {
        assertFalse(credentials.signUp(username, "1", context));
    }

    @Test
    public void logIn() {
        assertTrue(credentials.logIn(username, password, context));

    }

    @Test
    public void invalidLogin() {
        assertFalse(credentials.logIn("XyC", "Xyyyyvvv", context));
    }


}