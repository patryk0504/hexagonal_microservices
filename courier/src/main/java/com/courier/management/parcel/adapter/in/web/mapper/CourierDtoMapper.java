package com.courier.management.parcel.adapter.in.web.mapper;

import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.adapter.in.web.model.ShiftAddressDto;
import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.CourierShiftAddressDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface CourierDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    @Mapping(target = "parcels", ignore = true)
    @Mapping(target = "status", ignore = true)
    CourierDomain toCourierDomain(CourierDto courierDto);

    CourierDto toCourierDto(CourierDomain courierDomain);

    default Page<CourierDto> toCourierDtoPage(Page<CourierDomain> courierDomains) {
        return courierDomains.map(this::toCourierDto);
    }

    @Mapping(target = "shiftAddress", source = "address")
    CourierShiftAddressDomain toCourierShiftAddressDomain(ShiftAddressDto dto);

    List<CourierShiftAddressDomain> toCourierShiftAddressDomainList(List<ShiftAddressDto> dto);
}
