package com.route.application.service;

import com.route.adapter.in.web.model.model.AddressDto;
import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.adapter.in.web.model.model.ParcelDto;
import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.domain.AddressDomain;
import com.route.domain.TspRouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DomainMapper {

    default TspRouteDomain toTspRouteDomain(Set<ParcelDto> parcelDto, GeoStartAndEndPoints geoStartAndEndPoints) {
        List<AddressDomain> addressDomains = parcelDto.stream().map(ParcelDto::getAddress)
                .map(x -> x.stream().filter(r -> r.getRole().equalsIgnoreCase("recipient")).findFirst().orElseThrow())
                .map(a -> toAddressDomain(a.getAddress())).collect(Collectors.toCollection(ArrayList::new));

        addressDomains.add(0, toAddressDomain(geoStartAndEndPoints.getStartPoint()));
        addressDomains.add(toAddressDomain(geoStartAndEndPoints.getEndPoint()));
        return TspRouteDomain.builder().route(addressDomains).build();
    }

    @Mapping(target = "route", source = "addressRoute")
    TspRouteDomain toTspRouteDomain(AddressRouteDto addressRouteDto);

    @Mapping(target = "latitude", source = "geoAddress.latitude")
    @Mapping(target = "longitude", source = "geoAddress.longitude")
    AddressDomain toAddressDomain(AddressDto parcelDto);
}
