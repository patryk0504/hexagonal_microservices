package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.DeliveryManagementReadPort;
import com.courier.management.parcel.domain.DeliveryDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeliveryManagementReadAdapter implements DeliveryManagementReadPort {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryDomainMapper deliveryDomainMapper;

    @Override
    public Set<DeliveryDomain> getCourierDeliveries(long courierId) {
        Set<DeliveryEntity> deliveryEntities = deliveryRepository.findAllByCourierId(courierId);
        return deliveryDomainMapper.toDeliveryDomainSet(deliveryEntities);
    }
}