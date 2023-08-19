package com.courier.management.parcel.adapter.out.persistence.mapper;

import com.courier.management.parcel.adapter.out.persistence.entity.DeliveryEntity;
import com.courier.management.parcel.domain.DeliveryDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {ParcelDomainMapper.class})
public interface DeliveryDomainMapper {

    @Mapping(target = "courier", ignore = true)
    @Mapping(target = "parcels", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    DeliveryEntity toDeliveryEntity(DeliveryDomain deliveryDomain);

    @Mapping(target = "courierId", source = "courier.id")
    @Mapping(target = "parcels", expression = "java(deliveryEntity.getParcels().stream().map(x -> x.getId()).toList())")
    DeliveryDomain toDeliveryDomain(DeliveryEntity deliveryEntity);

    Set<DeliveryDomain> toDeliveryDomainSet(Set<DeliveryEntity> deliveryEntities);
}
