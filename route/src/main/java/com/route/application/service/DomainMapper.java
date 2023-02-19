package com.route.application.service;

import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.adapter.in.web.model.model.GeoAddressDto;
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
        List<AddressDomain> addressDomains = parcelDto.stream().map(this::toAddressDomain)
                .collect(Collectors.toCollection(ArrayList::new));
        addressDomains.add(0, toAddressDomain(geoStartAndEndPoints.getStartPoint()));
        addressDomains.add(toAddressDomain(geoStartAndEndPoints.getEndPoint()));
        return TspRouteDomain.builder().route(addressDomains).build();
    }

    @Mapping(target = "route", source = "addressRoute")
    TspRouteDomain toTspRouteDomain(AddressRouteDto addressRouteDto);

    AddressDomain toAddressDomain(GeoAddressDto geoAddressDto);

    @Mapping(target = "address", source = "recipientAddress")
    @Mapping(target = "latitude", source = "recipientGeoAddress.latitude")
    @Mapping(target = "longitude", source = "recipientGeoAddress.longitude")
    AddressDomain toAddressDomain(ParcelDto parcelDto);
}
