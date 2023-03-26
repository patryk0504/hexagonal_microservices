package com.route.adapter.in.web.model.mapper;

import com.route.adapter.in.web.model.model.AddressDto;
import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.domain.AddressDomain;
import com.route.domain.TspRouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DtoMapper {

    @Mapping(target = "addressRoute", source = "route")
    AddressRouteDto toAddressRouteResponse(TspRouteDomain tspRouteDomain);

    @Mapping(target = "geoAddress", expression = "java(toGeoAddressDto(addressDomain))")
    @Mapping(target = "simpleAddress", expression = "java(addressDomain.getFormattedAddress())")
    AddressDto toAddressDto(AddressDomain addressDomain);

    default AddressDto.GeoAddressDto toGeoAddressDto(AddressDomain addressDomain) {
        AddressDto.GeoAddressDto geoAddressDto = new AddressDto.GeoAddressDto();
        geoAddressDto.setLatitude(addressDomain.getLatitude());
        geoAddressDto.setLongitude(addressDomain.getLongitude());
        return geoAddressDto;
    }
}
