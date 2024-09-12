package com.dutq.lock.management.lock_management.repositories;

import com.dutq.lock.management.lock_management.dtos.ProfitByMonth;
import com.dutq.lock.management.lock_management.entites.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StayRepository extends JpaRepository<Stay, Long> {
    @Query("SELECT s FROM Stay s WHERE s.lockId = :lockId AND s.actualCheckoutTime IS NULL")
    Optional<Stay> findCurrentStayByLockId(int lockId);

    @Query("SELECT s from Stay s WHERE s.checkInTime BETWEEN :fromTime AND :toTime AND s.actualCheckoutTime IS NOT NULL")
    List<Stay> getAllStays(LocalDateTime fromTime, LocalDateTime toTime);

//    @Query("SELECT new com.dutq.lock.management.lock_management.dtos.ProfitByMonth(" +
//            "EXTRACT(YEAR FROM s.checkInTime)::int, EXTRACT(MONTH FROM s.checkInTime)::int, " +
//            "SUM(s.price), COUNT(s)) " +
//            "FROM Stay s " +
//            "WHERE EXTRACT(YEAR FROM s.checkInTime) = :year " +
//            "GROUP BY EXTRACT(YEAR FROM s.checkInTime), EXTRACT(MONTH FROM s.checkInTime) " +
//            "ORDER BY EXTRACT(MONTH FROM s.checkInTime)")
    @Query(value = "SELECT EXTRACT(YEAR FROM s.check_in_time) AS year, " +
        "EXTRACT(MONTH FROM s.check_in_time) AS month, " +
        "SUM(s.price) AS total_price, " +
        "COUNT(s.id) AS stay_count " +
        "FROM stay s " +
        "WHERE EXTRACT(YEAR FROM s.check_in_time) = :year " +
        "GROUP BY EXTRACT(YEAR FROM s.check_in_time), EXTRACT(MONTH FROM s.check_in_time) " +
        "ORDER BY EXTRACT(MONTH FROM s.check_in_time)",
        nativeQuery = true)
    List<Object[]> findTotalPriceGroupedByMonth(@Param("year") int year);
}
