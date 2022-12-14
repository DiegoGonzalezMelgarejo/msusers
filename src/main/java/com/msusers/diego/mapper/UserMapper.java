package com.msusers.diego.mapper;

import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.UserDto;
import com.msusers.diego.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {


    UserEntity  CreateDtoToEntity(CreateUserDto createUserDto);

}
