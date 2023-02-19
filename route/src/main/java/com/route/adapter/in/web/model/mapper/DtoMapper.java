package com.route.adapter.in.web.model.mapper;

import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.domain.TspRouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DtoMapper {

    @Mapping(target = "addressRoute", source = "route")
    AddressRouteDto toAddressRouteResponse(TspRouteDomain tspRouteDomain);
}
