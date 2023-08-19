package com.courier.management.parcel.adapter.in.web.mapper;

import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;
import com.courier.management.parcel.domain.DeliveryCreateDomain;
import com.courier.management.parcel.domain.DeliveryDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface DeliveryDtoMapper {
    DeliveryCreateDomain toDeliveryCreateDomain(DeliveryDto deliveryDto);

    @Mapping(target = "parcelIds", source = "parcels")
    DeliveryDto toDeliveryDto(DeliveryDomain deliveryDomain);

}
