package com.anthonytaha.rest.user;

import com.anthonytaha.rest.user.model.dto.UserDto;
import com.anthonytaha.rest.user.model.request.UserDetailsRequestModel;
import com.anthonytaha.rest.user.model.response.UserDetailsResponseModel;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDetailsResponseModel getUser(@PathVariable String id ){
        UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, userDetailsResponseModel);
        return userDetailsResponseModel;
    }

    @PostMapping
    public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails){
        UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, userDetailsResponseModel);
        return userDetailsResponseModel;
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }

}
