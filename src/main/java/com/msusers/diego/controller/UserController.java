package com.msusers.diego.controller;



import com.msusers.diego.dto.CreateUserDto;
import com.msusers.diego.dto.UserDto;
import com.msusers.diego.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create user")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @PostMapping()
    public ResponseEntity<UserDto> create(@Valid @RequestBody CreateUserDto createUserDto){
      return new ResponseEntity<>(userService.createUser(createUserDto),HttpStatus.CREATED);
    }
    @ApiOperation(value = "List users")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @GetMapping
    public  ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }
}
