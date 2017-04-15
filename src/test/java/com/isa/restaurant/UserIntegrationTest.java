package com.isa.restaurant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Milos on 11-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testAddingUser() throws Exception
    {
        this.mvc.perform(post("/users/register/sysManager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));

        this.mvc.perform(post("/users/register/sysManager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isConflict());

        this.mvc.perform(post("/users/register/barman")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));


        this.mvc.perform(post("/users/update/barman")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"username\":\"zika\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(content().json("{\"username\":\"zika\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));

    }

}
