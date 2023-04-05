package by.beatdev.beatdevtesttask.controllers;

import by.beatdev.beatdevtesttask.models.Users;
import by.beatdev.beatdevtesttask.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class ControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    Users userOne;
    Users userTwo;
    List<Users> userList = new ArrayList<Users>();
    Map<String, String> map;

    @BeforeEach
    void setUp() {
        userOne = new Users(1l, "Artem", "Eroshkin", "aaa", "aaa", "aaa");
        userTwo = new Users(2l, "Dima", "Koshkin", "aaa", "aaa", "aaa");
        userList.add(userOne);
        userList.add(userTwo);
        map = new HashMap();
        map.put("id", userOne.getId().toString());
        map.put("previous", "online");
        map.put("current", "offline");


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserById() throws Exception {
        when(userService.findById(anyLong())).thenReturn(userOne);
        mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void addUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userTwo);
        when(userService.save(userTwo)).thenReturn((Map<String, Object>) new HashMap<>().put("id", userTwo.getId()));
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateUserStatus() throws Exception {


        when(userService.updateUser(1)).thenReturn(map);
        mockMvc.perform(put("/user/1")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());
    }
}