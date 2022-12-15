package com.msusers.diego.service;

import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.UserDto;

import java.util.List;

public interface IUserService {
     UserDto createUser(CreateUserDto createUserDto);
     List<UserDto> getAll();
}
