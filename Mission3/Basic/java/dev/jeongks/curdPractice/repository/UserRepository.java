package dev.jeongks.curdPractice.repository;

import dev.jeongks.curdPractice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
