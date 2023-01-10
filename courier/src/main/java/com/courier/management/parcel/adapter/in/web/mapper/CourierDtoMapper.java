package com.courier.management.parcel.adapter.in.web.mapper;

import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.domain.CourierDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface CourierDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentLocation", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    @Mapping(target = "parcels", ignore = true)
    @Mapping(target = "status", ignore = true)
    CourierDomain toCourierDomain(CourierDto courierDto);
}
