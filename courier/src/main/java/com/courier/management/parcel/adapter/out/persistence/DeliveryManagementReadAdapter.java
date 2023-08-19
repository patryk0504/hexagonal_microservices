package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.DeliveryEntity;
import com.courier.management.parcel.adapter.out.persistence.mapper.DeliveryDomainMapper;
import com.courier.management.parcel.application.port.out.DeliveryManagementReadPort;
import com.courier.management.parcel.domain.DeliveryDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryManagementReadAdapter implements DeliveryManagementReadPort {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryDomainMapper deliveryDomainMapper;

    @Override
    public Optional<DeliveryDomain> getCourierDeliveries(long courierId) {
        Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findFirstByCourierIdOrderByCreatedOnAsc(courierId);
        return deliveryEntity.map(deliveryDomainMapper::toDeliveryDomain);
    }
}
