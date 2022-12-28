package com.route.adapter.in.web.model.mapper;

import com.route.adapter.in.web.model.response.AddressRouteResponse;
import com.route.domain.TspRouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    @Mapping(target = "addressRoute", source = "route")
    AddressRouteResponse toAddressRouteResponse(TspRouteDomain tspRouteDomain);
}
