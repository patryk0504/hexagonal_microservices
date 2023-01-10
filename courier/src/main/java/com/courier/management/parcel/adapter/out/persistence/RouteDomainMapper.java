package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.domain.RouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = ParcelDomainMapper.class)
public interface RouteDomainMapper {
    RouteEntity toRouteEntity(RouteDomain routeDomain);
}
