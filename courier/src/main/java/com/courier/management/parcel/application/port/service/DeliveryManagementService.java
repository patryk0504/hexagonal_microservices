package com.courier.management.parcel.application.port.service;

import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;
import com.courier.management.parcel.adapter.out.persistence.DeliveryDomainMapper;
import com.courier.management.parcel.application.port.in.CreateDeliveryUseCase;
import com.courier.management.parcel.application.port.in.GetDeliveriesUseCase;
import com.courier.management.parcel.application.port.out.DeliveryManagementReadPort;
import com.courier.management.parcel.application.port.out.DeliveryManagementWritePort;
import com.courier.management.parcel.domain.DeliveryDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryManagementService implements CreateDeliveryUseCase, GetDeliveriesUseCase {
    private final DeliveryManagementReadPort readPort;
    private final DeliveryManagementWritePort writePort;
    private final DeliveryDomainMapper deliveryDomainMapper;

    @Override
    public void createDelivery(long courierId, DeliveryDto deliveryDto) {
        writePort.createDelivery(courierId, deliveryDomainMapper.toDeliveryCreateDomain(deliveryDto));

    }

    //TODO: reimplement this
    @Override
    public DeliveryDto getCourierDeliveries(long courierId) {
        Set<DeliveryDomain> deliveryDomainSet = readPort.getCourierDeliveries(courierId);
        DeliveryDto deliveryDto = new DeliveryDto();
//        deliveryDto.setStatus(de);
        deliveryDto.setParcelIds(
                deliveryDomainSet.stream().map(d -> d.getParcel().getId()).collect(Collectors.toList()));
        return null;
    }
}
