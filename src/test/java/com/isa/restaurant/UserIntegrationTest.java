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
    public void testAddingSM() throws Exception
    {
        this.mvc.perform(post("/users/register/sysManager")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"email\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));

        this.mvc.perform(post("/users/register/sysManager")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddingOthers() throws Exception
    {

        /*this.mvc.perform(post("/users/register/barman")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));
                */

        /*
        this.mvc.perform(post("/users/update/barman")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"email\":\"zika\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(content().json("{\"username\":\"zika\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));
        */

        this.mvc.perform(post("/users/register/cook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"stefan\", \"password\":\"pera\", \"firstName\":\"Stefan\", \"lastName\":\"peric\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"stefan\", \"firstName\":\"Stefan\", \"lastName\":\"peric\"}"));

        this.mvc.perform(post("/users/register/bartender")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"sava1\", \"password\":\"sava1\", \"firstName\":\"sava1\", \"lastName\":\"peric1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"sava1\", \"firstName\":\"sava1\", \"lastName\":\"peric1\"}"));

        /*
        this.mvc.perform(post("/users/update/cook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"email\":\"sava2\", \"password\":\"sava2\", \"firstName\":\"sava2\", \"lastName\":\"sava2\"}"))
                .andExpect(content().json("{\"username\":\"sava2\", \"firstName\":\"sava2\", \"lastName\":\"sava2\"}"));

        this.mvc.perform(post("/users/update/bartender")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"email\":\"zika\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(content().json("{\"username\":\"zika\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));
        */
    }

}
