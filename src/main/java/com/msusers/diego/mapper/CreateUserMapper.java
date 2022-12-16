package com.msusers.diego.mapper;

import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface CreateUserMapper {

    CreateUserMapper MAPPER= Mappers.getMapper(CreateUserMapper.class);
    UserEntity  CreateDtoToEntity(CreateUserDto createUserDto);


}
