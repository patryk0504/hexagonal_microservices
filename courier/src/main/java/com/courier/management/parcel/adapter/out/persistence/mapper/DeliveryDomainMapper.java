package com.courier.management.parcel.adapter.out.persistence.mapper;

import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;
import com.courier.management.parcel.adapter.out.persistence.entity.DeliveryEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.RouteEntity;
import com.courier.management.parcel.domain.DeliveryCreateDomain;
import com.courier.management.parcel.domain.DeliveryDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {ParcelDomainMapper.class})
public interface DeliveryDomainMapper {

    @Mapping(target = "courier", ignore = true)
    @Mapping(target = "routes", ignore = true)
    DeliveryEntity toDeliveryEntity(DeliveryDomain deliveryDomain);

    @Mapping(target = "courierId", source = "courier.id")
    DeliveryDomain toDeliveryDomain(DeliveryEntity deliveryEntity);

    default List<Long> map(List<RouteEntity> routeEntities) {
        return routeEntities.stream().map(RouteEntity::getId).collect(Collectors.toList());
    }

    Set<DeliveryDomain> toDeliveryDomainSet(Set<DeliveryEntity> deliveryEntities);

    DeliveryCreateDomain toDeliveryCreateDomain(DeliveryDto deliveryDto);
}
