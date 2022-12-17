package com.msusers.diego.mapper;

import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateUserMapper {

    CreateUserMapper MAPPER= Mappers.getMapper(CreateUserMapper.class);
    UserEntity createDtoToEntity(CreateUserDto createUserDto);


}
