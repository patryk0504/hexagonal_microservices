package com.route.application.service;

import com.route.adapter.in.web.model.model.AddressDto;
import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.adapter.in.web.model.model.DeliveryCreateRequest;
import com.route.adapter.in.web.model.model.DeliveryDto;
import com.route.adapter.in.web.model.model.ParcelDto;
import com.route.adapter.in.web.model.model.ShiftAddressDto;
import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.domain.AddressDomain;
import com.route.domain.TspRouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DomainMapper {

    default TspRouteDomain toTspRouteDomain(Set<ParcelDto> parcelDto, GeoStartAndEndPoints geoStartAndEndPoints) {
        List<AddressDomain> addressDomains = parcelDto.stream().map(ParcelDto::getAddress)
                .map(x -> x.stream().filter(r -> r.getRole().equalsIgnoreCase("recipient")).findFirst().orElseThrow())
                .map(a -> toAddressDomain(a.getAddress())).collect(Collectors.toCollection(ArrayList::new));


        List<ParcelDto> parcelDtoList = new ArrayList<>(parcelDto);
        for (int i = 0; i < parcelDto.size(); i++) {
            addressDomains.get(i).setParcelId(parcelDtoList.get(i).getId());
        }

        addressDomains.add(0, toAddressDomain(geoStartAndEndPoints.getStartPoint()));
        addressDomains.add(toAddressDomain(geoStartAndEndPoints.getEndPoint()));
        return TspRouteDomain.builder().route(addressDomains).build();
    }

    @Mapping(target = "route", source = "addressRoute")
    TspRouteDomain toTspRouteDomain(AddressRouteDto addressRouteDto);

    @Mapping(target = "latitude", source = "geoAddress.latitude")
    @Mapping(target = "longitude", source = "geoAddress.longitude")
    @Mapping(target = "parcelId", ignore = true)
    AddressDomain toAddressDomain(AddressDto parcelDto);

    @Mapping(target = "delivery", source = "tspRouteDomain.route")
    @Mapping(target = "shiftAddress", source = "geoStartAndEndPoints")
    DeliveryCreateRequest toDeliveryCreateRequest(TspRouteDomain tspRouteDomain, GeoStartAndEndPoints geoStartAndEndPoints);


    @Mapping(target = "address", source = "addressDto")
    ShiftAddressDto toShiftAddress(AddressDto addressDto, String role);

    default DeliveryDto toDeliveryDto(List<AddressDomain> addressDomains) {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setParcelIds(addressDomains.stream().map(AddressDomain::getParcelId).filter(Objects::nonNull)
                .collect(Collectors.toList()));
        return deliveryDto;
    }

    default List<ShiftAddressDto> toShiftAddressList(GeoStartAndEndPoints geoStartAndEndPoints) {
        ShiftAddressDto start = toShiftAddress(geoStartAndEndPoints.getStartPoint(), "START");
        ShiftAddressDto end = toShiftAddress(geoStartAndEndPoints.getEndPoint(), "END");
        return List.of(start, end);
    }
}
