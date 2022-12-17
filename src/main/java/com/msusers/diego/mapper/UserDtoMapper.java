package com.msusers.diego.mapper;

import com.msusers.diego.dto.UserDto;
import com.msusers.diego.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper MAPPER= Mappers.getMapper(UserDtoMapper.class);
    UserDto entityToDto(UserEntity userEntity);
}
