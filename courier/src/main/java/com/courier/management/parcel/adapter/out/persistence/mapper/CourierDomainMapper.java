package com.courier.management.parcel.adapter.out.persistence.mapper;

import com.courier.management.parcel.adapter.out.persistence.entity.CourierEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.CourierShiftAddressEntity;
import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.CourierShiftAddressDomain;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {ParcelDomainMapper.class, DeliveryDomainMapper.class})
public
interface CourierDomainMapper {

    @IterableMapping(qualifiedByName = "toCourierShiftAddressEntity")
    CourierEntity toCourierEntity(CourierDomain courierDomain);

    @Mapping(target = "shiftAddress", source = "shiftAddress")
    CourierDomain toCourierDomain(CourierEntity courierEntity);

    default Page<CourierDomain> toCourierDomainPage(Page<CourierEntity> courierEntityPage) {
        return courierEntityPage.map(this::toCourierDomain);
    }

    @Mapping(target = "courier", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address.shifts", ignore = true)
    @Mapping(target = "address", source = "shiftAddress")
    CourierShiftAddressEntity toCourierShiftAddressEntity(CourierShiftAddressDomain domain);

    @Mapping(target = "shiftAddress", source = "address")
    CourierShiftAddressDomain toCourierShiftAddressEntity(CourierShiftAddressEntity entity);

}
