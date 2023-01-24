package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.domain.DeliveryDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {ParcelDomainMapper.class, CourierDomainMapper.class})
public interface DeliveryDomainMapper {

    @Mapping(target = "courier", source = "courier")
    DeliveryEntity toDeliveryEntity(DeliveryDomain deliveryDomain);
}
