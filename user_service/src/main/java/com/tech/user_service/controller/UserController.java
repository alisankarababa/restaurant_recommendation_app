package com.tech.user_service.controller;

import com.tech.common.response.RestResponse;
import com.tech.user_service.dto.UserDto;
import com.tech.user_service.entity.User;
import com.tech.user_service.mapper.IUserMapper;
import com.tech.user_service.request_body.UserRequestBody;
import com.tech.user_service.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDto>> save(@RequestBody @Valid UserRequestBody requestBody) {

        User user = IUserMapper.INSTANCE.userRequestBodyToUser(requestBody);
        User savedUser = userService.save(user);
        UserDto userDto = IUserMapper.INSTANCE.userToUserDto(savedUser);

        return new ResponseEntity<>(RestResponse.ok(userDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDto>>> find() {

        List<User> userList = userService.findAll();
        List<UserDto> userDtos = IUserMapper.INSTANCE.userListToUserDtoList(userList);
        return new ResponseEntity<>(RestResponse.ok(userDtos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDto>> findById(@PathVariable @Positive long id) {

        User user = userService.findById(id);
        UserDto userDto = IUserMapper.INSTANCE.userToUserDto(user);
        return new ResponseEntity<>(RestResponse.ok(userDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserDto>> update(@PathVariable @Positive long id,
                                                        @RequestBody @Valid UserRequestBody requestBody) {

        User userFound = userService.findById(id);
        IUserMapper.INSTANCE.updateUser(userFound, requestBody);
        User updatedUser = userService.save(userFound);
        UserDto userDto = IUserMapper.INSTANCE.userToUserDto(updatedUser);
        return new ResponseEntity<>(RestResponse.ok(userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> delete(@PathVariable @Positive long id) {

        userService.deleteById(id);
        return new ResponseEntity<>(RestResponse.noContent(), HttpStatus.NO_CONTENT);
    }
}