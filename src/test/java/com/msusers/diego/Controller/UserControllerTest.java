package com.msusers.diego.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msusers.diego.controller.UserController;
import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.PhoneDto;
import com.msusers.diego.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
 class UserControllerTest {

    @Autowired
    @InjectMocks
    private  UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
     void create_user_success_ok() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/")
                        .content(objectMapper.writeValueAsString(getCreateUserDto()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        int message=mvcResult.getResponse().getStatus();
      Assertions.assertEquals(HttpStatus.CREATED.value(),message);
    }
    @Test
     void create_user_success_error_email() throws Exception {
    CreateUserDto createUserDto=getCreateUserDto();
    createUserDto.setEmail("a");
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/")
                        .content(objectMapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest()).andReturn();

        int status=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),status);
    }
    @Test
     void create_user_success_error_password() throws Exception {
        CreateUserDto createUserDto=getCreateUserDto();
        createUserDto.setPassword("a");
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/")
                        .content(objectMapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest()).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),message);
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

}
