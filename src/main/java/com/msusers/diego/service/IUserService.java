package com.msusers.diego.service;

import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.UserDto;

public interface IUserService {
     UserDto createUser(CreateUserDto createUserDto);
}
