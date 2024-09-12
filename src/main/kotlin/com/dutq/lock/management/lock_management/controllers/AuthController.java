package com.dutq.lock.management.lock_management.controllers;

import com.dutq.lock.management.lock_management.config.UserAuthenticationProvider;
import com.dutq.lock.management.lock_management.dtos.CredentialsDto;
import com.dutq.lock.management.lock_management.dtos.SignUpDto;
import com.dutq.lock.management.lock_management.dtos.UserDto;
import com.dutq.lock.management.lock_management.services.UserService;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

  private final UserService userService;
  private final UserAuthenticationProvider userAuthenticationProvider;

  @PostMapping("/login")
  public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
    UserDto userDto = userService.login(credentialsDto);
    userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
    return ResponseEntity.ok(userDto);
  }

  @PostMapping("/register")
  public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
    UserDto createdUser = userService.register(user);
    createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
    return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
  }
}
