package com.msusers.diego.service;

import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.PhoneDto;
import com.msusers.diego.dto.UserDto;
import com.msusers.diego.entities.ParameterEntity;
import com.msusers.diego.entities.PhoneEntity;
import com.msusers.diego.entities.UserEntity;
import com.msusers.diego.exception.UserException;
import com.msusers.diego.mapper.CreateUserMapper;
import com.msusers.diego.mapper.UserDtoMapper;
import com.msusers.diego.repository.IParameterRepository;
import com.msusers.diego.repository.UserRepository;
import com.msusers.diego.service.serviceimpl.UserServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 class UserServiceTest {

    private CreateUserMapper createUserMapper;

    private UserDtoMapper userDtoMapper;
    private IParameterRepository parameterRepository;
    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        createUserMapper = mock(CreateUserMapper.class);
        parameterRepository = mock(IParameterRepository.class);
        userRepository = mock(UserRepository.class);
        userDtoMapper=mock(UserDtoMapper.class);
        this.userService= new UserServiceImpl(parameterRepository,userRepository);

    }
    @Test
     void create_user_ok(){
       ParameterEntity parameterEntity=getParameterEntity();
        when(parameterRepository.findByName(any())).thenReturn(parameterEntity);
        CreateUserDto createUserDto= getCreateUserDto();
         UserEntity userEntity=getUserEntity();
        when(createUserMapper.createDtoToEntity(any())).thenReturn(userEntity);
        when(userRepository.save(any())).thenReturn(userEntity);
        UserDto user=userService.createUser(createUserDto);
        Assertions.assertNotNull(user);
    }
    @Test
     void create_user_password_invalid(){
        ParameterEntity parameterEntity=getParameterEntity();
        when(parameterRepository.findByName(any())).thenReturn(parameterEntity);
        CreateUserDto createUserDto= getCreateUserDto();
        createUserDto.setPassword("a");
        UserEntity userEntity=getUserEntity();
        when(createUserMapper.createDtoToEntity(any())).thenReturn(userEntity);
        when(userRepository.save(any())).thenReturn(userEntity);
        UserException userException= Assertions.assertThrows(UserException.class,()->{
            UserDto user=userService.createUser(createUserDto);
        });

        Assertions.assertNotNull(userException);
    }
    private ParameterEntity getParameterEntity(){
        ParameterEntity parameterEntity= new ParameterEntity();
        parameterEntity.setId("as");
        parameterEntity.setId("password");
        parameterEntity.setPattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}");
        return parameterEntity;
    }

    private CreateUserDto getCreateUserDto(){
        CreateUserDto createUserDto= new CreateUserDto();
        createUserDto.setEmail("diegoaliriogm@gmail.com");
        createUserDto.setName("diego");
        createUserDto.setPassword("A!@#&()–a1");
        PhoneDto phoneDto=new PhoneDto();
        phoneDto.setNumber("31");
        phoneDto.setContryCode("4");
        phoneDto.setCityCode("31");
        List<PhoneDto> phoneDtos= new ArrayList<>();
        phoneDtos.add(phoneDto);
        createUserDto.setPhones(phoneDtos);
        return  createUserDto;

    }

    private UserEntity getUserEntity(){
        UserEntity userEntity= new UserEntity();
        userEntity.setToken("asdasd");
        userEntity.setActive(true);
        userEntity.setCreated(LocalDate.now());
        userEntity.setLastLogin(LocalDate.now());
        userEntity.setPassword("asdas");
        userEntity.setId("asdasdas");
        PhoneEntity phoneEntity= new PhoneEntity();
        phoneEntity.setUser(userEntity);
        phoneEntity.setNumber("12");
        phoneEntity.setContryCode("as");
        phoneEntity.setCityCode("as");
        List<PhoneEntity>phoneEntities= new ArrayList<>();
        phoneEntities.add(phoneEntity);
        userEntity.setPhones(phoneEntities);
        return userEntity;
    }

    @Test
     void getAll_success_ok(){
        List<UserEntity> userEntities= new ArrayList<>();
        userEntities.add(getUserEntity());
        when(userRepository.findAll()).thenReturn(userEntities);
        when(userDtoMapper.entityToDto(any())).thenReturn(getUserDto());
        Assertions.assertEquals(1,userService.getAll().size());
    }

    private UserDto getUserDto(){
        return new UserDto();
    }
}
