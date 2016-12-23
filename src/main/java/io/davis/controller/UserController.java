package io.davis.controller;

import io.davis.entity.User;
import io.davis.model.UserDraft;
import io.davis.service.UserService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Davis on 16/12/23.
 */
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @ApiOperation("create user")
  @PostMapping("/users")
  public User create(@RequestBody
                     @ApiParam(value = "User Draft", required = true)
                     @Valid UserDraft draft) {
    User user = userService.createUser(draft);
    return user;
  }

  @ApiOperation("get user by id")
  @GetMapping("/users/{id}")
  public User getUser(@PathVariable(value = "id") String id) {
    return userService.getUser(id);
  }

  @ApiOperation("get all users")
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
