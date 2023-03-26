package com.courier.management.parcel.adapter.in.web.mapper;

import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.domain.ParcelDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface ParcelDtoMapper {
    @Mapping(target = "id", ignore = true)
    ParcelDomain toParcelDomain(ParcelDto parcelDto);

    ParcelDto toParcelDto(ParcelDomain parcelDomain);

    Set<ParcelDto> toParcelDtoSet(Set<ParcelDomain> parcelDomainSet);

    default Page<ParcelDto> toParcelDtoPage(Page<ParcelDomain> parcelDomains) {
        return parcelDomains.map(this::toParcelDto);
    }
}
