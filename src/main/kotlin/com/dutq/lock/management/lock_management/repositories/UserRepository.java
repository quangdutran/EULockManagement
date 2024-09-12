package com.dutq.lock.management.lock_management.repositories;

import com.dutq.lock.management.lock_management.entites.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLogin(String login);
}
