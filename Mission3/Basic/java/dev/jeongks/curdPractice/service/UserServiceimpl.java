package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.UserDto;
import dev.jeongks.curdPractice.entity.UserEntity;
import dev.jeongks.curdPractice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceimpl.class);
    private UserRepository userRepository;

    public UserServiceimpl(
            @Autowired UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setWriter(userDto.getWriter());
        userEntity.setPassword(userDto.getPassword());
        userEntity = this.userRepository.save(userEntity);

        UserDto newUser = new UserDto();
        newUser.setId(userEntity.getId());
        newUser.setWriter(userEntity.getWriter());
        newUser.setPassword(userEntity.getPassword());
        return newUser;
    }

    @Override
    public UserDto readUser(Long id) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        UserEntity userEntity = optionalUserEntity.get();
        UserDto findUser = new UserDto();
        findUser.setId(userEntity.getId());
        findUser.setWriter(userEntity.getWriter());
        findUser.setPassword(userEntity.getPassword());
        return findUser;
    }

    @Override
    public Collection<UserDto> readUserAll() {
        Iterable<UserEntity> userEntityIterable = this.userRepository.findAll();
        List<UserDto> list = new ArrayList<>();
        for(UserEntity user : userEntityIterable){
            list.add(new UserDto(
               user.getId(),
               user.getWriter(),
               user.getPassword()
            ));
        }
        return list;
    }

    @Override
    public boolean updateUser(Long id, UserDto userDto) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        if(optionalUserEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserEntity userEntity = optionalUserEntity.get();
        userEntity.setPassword(userDto.getPassword()!=null ? userDto.getPassword() : userEntity.getPassword());
        this.userRepository.save(userEntity);
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        if(optionalUserEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = optionalUserEntity.get();
        this.userRepository.delete(userEntity);
        return true;
    }
}
