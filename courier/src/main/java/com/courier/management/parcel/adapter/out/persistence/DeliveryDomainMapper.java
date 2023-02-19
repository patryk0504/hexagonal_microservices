package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.domain.DeliveryDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {ParcelDomainMapper.class, RouteDomainMapper.class})
public interface DeliveryDomainMapper {

    @Mapping(target = "courier", ignore = true)
    DeliveryEntity toDeliveryEntity(DeliveryDomain deliveryDomain);

    @Mapping(target = "courierId", source = "courier.id")
    DeliveryDomain toDeliveryDomain(DeliveryEntity deliveryEntity);
}
