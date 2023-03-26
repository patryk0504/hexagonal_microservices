package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.domain.AddressDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface AddressDomainMapper {
    AddressDomain toAddressDomain(AddressEntity address);
}
