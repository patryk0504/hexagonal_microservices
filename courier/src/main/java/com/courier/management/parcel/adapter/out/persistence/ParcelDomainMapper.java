package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.domain.ParcelDomain;
import com.courier.management.parcel.domain.ParcelStatusDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
interface ParcelDomainMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "courier", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "deliveryDate", ignore = true)
    ParcelEntity toParcelEntity(ParcelDomain parcelDomain);

    @Mapping(target = "user", source = "sender.id")
    ParcelDomain toParcelDomain(ParcelEntity parcelEntity);

    Set<ParcelDomain> toParcelDomainSet(Set<ParcelEntity> parcelEntitySet);

    ParcelEntity.ParcelStatus toParcelEntityStatus(ParcelStatusDomain parcelStatusDomain);

    default Page<ParcelDomain> toParcelDomainPage(Page<ParcelEntity> parcelEntities) {
        return parcelEntities.map(this::toParcelDomain);
    }
}
