package com.dutq.lock.management.lock_management.repositories;

import com.dutq.lock.management.lock_management.entites.PassCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PassCodeRepository extends JpaRepository<PassCode, Long> {
    @Query("SELECT p from PassCode p WHERE p.lockId = :lockId AND p.to < :currentTime AND p.deletedOnCloud = false")
    List<PassCode> findAllActivePassCodeByLockId(int lockId, LocalDateTime currentTime);
}
