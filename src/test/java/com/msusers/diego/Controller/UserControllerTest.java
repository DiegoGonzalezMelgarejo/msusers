package com.msusers.diego.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.msusers.diego.controller.UserController;
import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.PhoneDto;
import com.msusers.diego.dto.UserDto;
import com.msusers.diego.repository.IParameterRepository;
import com.msusers.diego.repository.UserRepository;
import com.msusers.diego.service.IUserService;
import com.msusers.diego.service.serviceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    @InjectMocks
    private  UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void create_user_success_ok() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/")
                        .content(objectMapper.writeValueAsString(getCreateUserDto()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        int message=mvcResult.getResponse().getStatus();
      Assert.assertEquals(HttpStatus.CREATED.value(),message);
    }
    @Test
    public void create_user_success_error_email() throws Exception {
    CreateUserDto createUserDto=getCreateUserDto();
    createUserDto.setEmail("a");
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/")
                        .content(objectMapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest()).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),message);
    }
    @Test
    public void create_user_success_error_password() throws Exception {
        CreateUserDto createUserDto=getCreateUserDto();
        createUserDto.setPassword("a");
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/")
                        .content(objectMapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest()).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),message);
    }
    private UserDto getUserDto() {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setCityCode("");
        phoneDto.setNumber("");
        phoneDto.setContryCode("");
        List<PhoneDto> phoneDtos = new ArrayList<>();
        phoneDtos.add(phoneDto);

        UserDto userDto =new UserDto();
        userDto.setId("");
        userDto.setEmail("");
        userDto.setName("");
        userDto.setToken("");
        userDto.setLastLogin(LocalDate.now());
        userDto.setPassword("asasdasd");
        return userDto;
    }

    private CreateUserDto getCreateUserDto() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("diegoaliriogm@gmail.com");
        createUserDto.setName("diego");
        createUserDto.setPassword("A!@#&()–a1");
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("31");
        phoneDto.setContryCode("4");
        phoneDto.setCityCode("31");
        List<PhoneDto> phoneDtos = new ArrayList<>();
        phoneDtos.add(phoneDto);
        createUserDto.setPhones(phoneDtos);
        return createUserDto;

    }

    public static void main(String[] args) {
        String a="xabbcacpqr";
        
    }
}
