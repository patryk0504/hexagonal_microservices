package com.route.application.service;

import com.route.adapter.in.web.model.AddressListDto;
import com.route.adapter.in.web.model.request.AddressListRequest;
import com.route.domain.TspRouteDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DomainMapper {

    @Mapping(target = "route", source = "addressList")
    TspRouteDomain toTspRouteDomain(AddressListDto addressListDto);

    @Mapping(target = "addressList", source = "addressList")
    AddressListDto toCityListDto(AddressListRequest addressListRequest);
}
