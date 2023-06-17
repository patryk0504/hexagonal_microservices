package com.courier.management.parcel.adapter.out.persistence.mapper;

import com.courier.management.parcel.adapter.out.persistence.entity.CourierEntity;
import com.courier.management.parcel.domain.CourierDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {ParcelDomainMapper.class, DeliveryDomainMapper.class, RouteDomainMapper.class})
public
interface CourierDomainMapper {

    CourierEntity toCourierEntity(CourierDomain courierDomain);

    CourierDomain toCourierDomain(CourierEntity courierEntity);

    default Page<CourierDomain> toCourierDomainPage(Page<CourierEntity> courierEntityPage) {
        return courierEntityPage.map(this::toCourierDomain);
    }
}