package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.domain.CourierDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = ParcelDomainMapper.class)
interface CourierDomainMapper {

    CourierEntity toCourierEntity(CourierDomain courierDomain);
}
