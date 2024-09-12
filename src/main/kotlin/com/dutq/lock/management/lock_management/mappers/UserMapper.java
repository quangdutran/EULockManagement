package com.dutq.lock.management.lock_management.mappers;

import com.dutq.lock.management.lock_management.dtos.SignUpDto;
import com.dutq.lock.management.lock_management.dtos.UserDto;
import com.dutq.lock.management.lock_management.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toUserDto(User user);

  @Mapping(target = "password", ignore = true)
  User signUpToUser(SignUpDto signUpDto);
}
