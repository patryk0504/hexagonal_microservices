package com.courier.management.parcel.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface UserDomainMapper {
    default Long map(UserEntity user) {
        return user.getId();
    }
}
