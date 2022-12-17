package com.msusers.diego.service.serviceimpl;

import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.PhoneDto;
import com.msusers.diego.dto.UserDto;
import com.msusers.diego.entities.PhoneEntity;
import com.msusers.diego.entities.UserEntity;
import com.msusers.diego.exception.UserException;
import com.msusers.diego.mapper.CreateUserMapper;
import com.msusers.diego.mapper.UserDtoMapper;
import com.msusers.diego.repository.IParameterRepository;
import com.msusers.diego.repository.UserRepository;
import com.msusers.diego.service.IUserService;
import com.msusers.diego.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {




    @Autowired
    private IParameterRepository parameterRepository;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl( IParameterRepository parameterRepository, UserRepository userRepository) {

        this.parameterRepository = parameterRepository;
        this.userRepository = userRepository;

    }


    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        String regedex = parameterRepository.findByName("password").getPattern();
        if (!Utilities.isValid(createUserDto.getPassword(), Pattern.compile(regedex)))
            throw new UserException("La contraseña no cumple con el parametro establecido");
        UserEntity userEntity = formatUserEntity(createUserDto);
        List<PhoneEntity> phoneEntities = getPhoneEntites(createUserDto.getPhones(), userEntity);
        userEntity.setPhones(phoneEntities);
        UserEntity user = userRepository.save(userEntity);
        return formatUserDto( user);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> userEntities=userRepository.findAll();
        return userEntities.stream().map(UserDtoMapper.MAPPER::entityToDto).collect(Collectors.toList());

    }

    public UserDto formatUserDto( UserEntity user) {
       return UserDtoMapper.MAPPER.entityToDto(user);

    }

    private UserEntity formatUserEntity(CreateUserDto createUserDto) {
        UserEntity userEntity = CreateUserMapper.MAPPER.createDtoToEntity(createUserDto);
        userEntity.setCreated(LocalDate.now());
        userEntity.setPassword(Utilities.encryptText(userEntity.getPassword()));
        userEntity.setLastLogin(LocalDate.now());
        userEntity.setActive(true);
        userEntity.setToken(getJWTToken(createUserDto));
        return userEntity;
    }

    private List<PhoneEntity> getPhoneEntites(List<PhoneDto> phoneDtos, UserEntity userEntity) {
        return phoneDtos.parallelStream().map(phoneDto -> {
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setCityCode(phoneDto.getCityCode());
            phoneEntity.setNumber(phoneDto.getNumber());
            phoneEntity.setContryCode(phoneDto.getContryCode());
            phoneEntity.setUser(userEntity);
            return phoneEntity;
        }).collect(Collectors.toList());

    }
    private String getJWTToken(CreateUserDto createUserDto) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId(createUserDto.getEmail())
                .setSubject(createUserDto.getName())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
