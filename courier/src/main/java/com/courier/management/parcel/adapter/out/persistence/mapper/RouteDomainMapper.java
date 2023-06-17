package com.courier.management.parcel.adapter.out.persistence.mapper;

import com.courier.management.parcel.adapter.out.persistence.entity.RouteEntity;
import com.courier.management.parcel.domain.RouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {ParcelDomainMapper.class})
public interface RouteDomainMapper {
    @Mapping(target = "courier", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    RouteEntity toRouteEntity(RouteDomain routeDomain);

    @Mapping(target = "deliveryId", source = "delivery.id")
    @Mapping(target = "courierId", source = "courier.id")
    RouteDomain toRouteDomain(RouteEntity routeEntity);
}
