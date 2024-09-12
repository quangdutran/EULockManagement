package com.dutq.lock.management.lock_management.repositories;

import com.dutq.lock.management.lock_management.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
