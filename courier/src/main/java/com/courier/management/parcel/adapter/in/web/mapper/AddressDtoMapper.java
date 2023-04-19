package com.courier.management.parcel.adapter.in.web.mapper;

import com.courier.management.parcel.adapter.in.web.model.AddressDto;
import com.courier.management.parcel.domain.AddressDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface AddressDtoMapper {
    List<AddressDto> toAddressDto(List<AddressDomain> addressDomains);

    AddressDomain toAddressDomain(AddressDto addressDto);
}
