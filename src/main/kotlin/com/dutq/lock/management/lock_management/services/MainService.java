package com.dutq.lock.management.lock_management.services;

import com.dutq.lock.management.lock_management.dtos.CheckinRequest;
import com.dutq.lock.management.lock_management.dtos.ProfitByMonth;
import com.dutq.lock.management.lock_management.entites.PassCode;
import com.dutq.lock.management.lock_management.entites.Stay;
import com.dutq.lock.management.lock_management.mappers.CheckinMapper;
import com.dutq.lock.management.lock_management.repositories.PassCodeRepository;
import com.dutq.lock.management.lock_management.repositories.StayRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MainService {
  private final StayRepository stayRepository;
  private final PassCodeRepository passCodeRepository;

  public List<PassCode> getPassCodesByLockId(int lockId) {
    return passCodeRepository.findAllActivePassCodeByLockId(lockId, LocalDateTime.now());
  }

  public Optional<Stay> getCurrentStayByLockId(int lockId) {
    return stayRepository.findCurrentStayByLockId(lockId);
  }

  public Stay checkoutStay(long stayID) {
    Optional<Stay> stay = stayRepository.findById(stayID);
    if (stay.isPresent()) {
      Stay result = stay.get();
      result.setActualCheckoutTime(LocalDateTime.now());
      stayRepository.save(result);
      return result;
    } else {
      throw new EntityNotFoundException("Could not find the stay with id " + stayID);
    }
  }

  public Stay checkin(CheckinRequest checkinRequest) {
    CheckinMapper checkinMapper = Mappers.getMapper(CheckinMapper.class);
    Stay stay = checkinMapper.checkinRequestToStay(checkinRequest);
    stayRepository.save(stay);
    return stay;
  }

  public List<Stay> getAllStays(long from, long to) {
    return stayRepository.getAllStays(
        Instant.ofEpochMilli(from).atZone(ZoneId.systemDefault()).toLocalDateTime(),
        Instant.ofEpochMilli(to).atZone(ZoneId.systemDefault()).toLocalDateTime());
  }

  public List<ProfitByMonth> getProfitByYear(int year) {
    List<Object[]> results = stayRepository.findTotalPriceGroupedByMonth(year);
    return results.stream()
        .map(
            row ->
                new ProfitByMonth(
                    ((Number) row[0]).intValue(), // year
                    ((Number) row[1]).intValue(), // month
                    ((Number) row[2]).doubleValue(), // total price
                    ((Number) row[3]).intValue() // stay count
                    ))
        .collect(Collectors.toList());
  }
}
