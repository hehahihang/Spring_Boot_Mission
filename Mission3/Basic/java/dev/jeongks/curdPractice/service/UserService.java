package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.UserDto;

import java.util.Collection;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto readUser(Long id);
    Collection<UserDto> readUserAll();
    boolean updateUser(Long id,UserDto userDto);
    boolean deleteUser(Long id);
}
