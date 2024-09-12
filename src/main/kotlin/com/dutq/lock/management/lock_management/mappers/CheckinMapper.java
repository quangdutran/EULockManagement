package com.dutq.lock.management.lock_management.mappers;

import com.dutq.lock.management.lock_management.dtos.CheckinRequest;
import com.dutq.lock.management.lock_management.entites.Stay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CheckinMapper {
    @Mapping(source = "checkoutTime", target = "expectedCheckoutTime")
    @Mapping(source = "customerEmail", target = "customerEmail")
    Stay checkinRequestToStay(CheckinRequest request);
}
