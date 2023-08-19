package com.courier.management.parcel.application.port.service;

import com.courier.management.parcel.adapter.in.web.mapper.CourierDtoMapper;
import com.courier.management.parcel.adapter.in.web.mapper.DeliveryDtoMapper;
import com.courier.management.parcel.adapter.in.web.model.DeliveryCreateRequest;
import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;
import com.courier.management.parcel.application.port.in.CreateDeliveryUseCase;
import com.courier.management.parcel.application.port.in.GetDeliveriesUseCase;
import com.courier.management.parcel.application.port.out.DeliveryManagementReadPort;
import com.courier.management.parcel.application.port.out.DeliveryManagementWritePort;
import com.courier.management.parcel.domain.CourierShiftAddressDomain;
import com.courier.management.parcel.domain.DeliveryCreateDomain;
import com.courier.management.parcel.domain.DeliveryDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeliveryManagementService implements CreateDeliveryUseCase, GetDeliveriesUseCase {
    private final DeliveryManagementReadPort readPort;
    private final DeliveryManagementWritePort writePort;
    private final DeliveryDtoMapper deliveryDtoMapper;
    private final CourierDtoMapper courierDtoMapper;

    @Override
    public DeliveryDto createDelivery(long courierId, DeliveryCreateRequest deliveryCreateRequest) {
        DeliveryCreateDomain deliveryCreateDomain = deliveryDtoMapper.toDeliveryCreateDomain(
                deliveryCreateRequest.getDelivery());
        List<CourierShiftAddressDomain> courierShiftAddressDomain = courierDtoMapper.toCourierShiftAddressDomainList(
                deliveryCreateRequest.getShiftAddress());

        DeliveryDomain deliveryDomain = writePort.createDelivery(courierId, deliveryCreateDomain,
                courierShiftAddressDomain);
        return deliveryDtoMapper.toDeliveryDto(deliveryDomain);
    }

    //TODO: reimplement this
    @Transactional(readOnly = true)
    @Override
    public DeliveryDto getCourierDeliveries(long courierId) {
        Optional<DeliveryDomain> deliveryDomain = readPort.getCourierDeliveries(courierId);
        DeliveryDto deliveryDto = deliveryDomain.map(deliveryDtoMapper::toDeliveryDto).orElse(null);
        log.info("Received {}", deliveryDto);
        return deliveryDomain.map(deliveryDtoMapper::toDeliveryDto).orElse(null);
    }
}
