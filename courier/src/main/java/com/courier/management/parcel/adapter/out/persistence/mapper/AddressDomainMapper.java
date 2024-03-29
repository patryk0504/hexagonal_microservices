package com.courier.management.parcel.adapter.out.persistence.mapper;

import com.courier.management.parcel.adapter.out.persistence.entity.AddressEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.ShiftAddressEntity;
import com.courier.management.parcel.domain.AddressDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface AddressDomainMapper {
    AddressDomain toAddressDomain(AddressEntity address);

    List<AddressDomain> toAddressDomainList(List<AddressEntity> addressEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "parcel", ignore = true)
    AddressEntity toAddressEntity(AddressDomain addressDomain);

    @Mapping(target = "shifts", ignore = true)
    ShiftAddressEntity toShiftAddressEntity(AddressDomain addressDomain);
}
