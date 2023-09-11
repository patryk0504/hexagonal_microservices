package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.CourierAddressRoleEnum;
import com.courier.management.parcel.adapter.out.persistence.entity.CourierEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.DeliveryEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.ParcelEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.ShiftAddressEntity;
import com.courier.management.parcel.adapter.out.persistence.mapper.AddressDomainMapper;
import com.courier.management.parcel.adapter.out.persistence.mapper.DeliveryDomainMapper;
import com.courier.management.parcel.application.port.out.DeliveryManagementWritePort;
import com.courier.management.parcel.domain.CourierShiftAddressDomain;
import com.courier.management.parcel.domain.DeliveryCreateDomain;
import com.courier.management.parcel.domain.DeliveryDomain;
import com.courier.management.parcel.domain.DeliveryStatusDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryManagementWriteAdapter implements DeliveryManagementWritePort {
    private final CourierRepository courierRepository;
    private final ParcelRepository parcelRepository;
    private final ShiftAddressRepository shiftAddressRepository;
    private final AddressDomainMapper addressDomainMapper;
    private final DeliveryDomainMapper deliveryDomainMapper;

    @Override
    public DeliveryDomain createDelivery(long courierId, DeliveryCreateDomain deliveryCreateDomain, List<CourierShiftAddressDomain> courierShiftAddressDomain) {
        Optional<CourierEntity> courier = courierRepository.findById(courierId);
        if (courier.isEmpty()) {
            log.error("There is no courier with id = {}", courier);
            throw new NotFoundException("There is no courier with given id");
        }

        if (!courierShiftAddressDomain.isEmpty()) {
            updateCourierShiftAddress(courierShiftAddressDomain, courier);
        }

        Set<ParcelEntity> parcels = parcelRepository.findAllByIdIn(deliveryCreateDomain.getParcelIds());
        if (parcels.isEmpty() || parcels.size() != deliveryCreateDomain.getParcelIds().size()) {
            log.error("There is no parcel with given ids = {}", deliveryCreateDomain.getClass());
            throw new NotFoundException("There is no parcels for given ids");
        }

//        Set<DeliveryEntity> deliveries = deliveryRepository.findAllByCourierId(courierId);

        DeliveryEntity delivery = new DeliveryEntity();
        delivery.setCourier(courier.get());
        Optional<DeliveryStatusDomain> statusDomain = Optional.ofNullable(deliveryCreateDomain.getStatus());
        delivery.setStatus(statusDomain.isPresent() ? DeliveryEntity.DeliveryStatus.fromString(
                statusDomain.get().name()) : DeliveryEntity.DeliveryStatus.IN_PROGRESS);
        delivery.setNotes(deliveryCreateDomain.getNotes());

        parcels.forEach(delivery::addParcel);

        List<ParcelEntity> parcelEntityList = new ArrayList<>(parcels);
        for (int i = 0; i < parcels.size(); i++) {
            parcelEntityList.get(i).setDeliveryOrder(i);
        }

        log.info("Saving new delivery: {}", delivery);
        return deliveryDomainMapper.toDeliveryDomain(delivery);
    }

    private void updateCourierShiftAddress(List<CourierShiftAddressDomain> courierShiftAddressDomain, Optional<CourierEntity> courier) {
        ShiftAddressEntity startShiftAddress = addressDomainMapper.toShiftAddressEntity(
                courierShiftAddressDomain.get(0).getShiftAddress());
        ShiftAddressEntity endShiftAddress = addressDomainMapper.toShiftAddressEntity(
                courierShiftAddressDomain.get(1).getShiftAddress());

        shiftAddressRepository.saveAll(List.of(startShiftAddress, endShiftAddress));

        log.info("New shift addresses, START: {}, END: {}", startShiftAddress, endShiftAddress);

        courier.get().addAddress(startShiftAddress, CourierAddressRoleEnum.START);
        courier.get().addAddress(endShiftAddress, CourierAddressRoleEnum.END);
    }
}
