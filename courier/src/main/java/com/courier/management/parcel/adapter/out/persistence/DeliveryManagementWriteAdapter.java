package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.CourierEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.DeliveryEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.ParcelEntity;
import com.courier.management.parcel.application.port.out.DeliveryManagementWritePort;
import com.courier.management.parcel.domain.DeliveryCreateDomain;
import com.courier.management.parcel.domain.DeliveryStatusDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryManagementWriteAdapter implements DeliveryManagementWritePort {
    private final DeliveryRepository deliveryRepository;
    private final CourierRepository courierRepository;
    private final ParcelRepository parcelRepository;

    @Override
    public void createDelivery(long courierId, DeliveryCreateDomain deliveryCreateDomain) {
        Optional<CourierEntity> courier = courierRepository.findById(courierId);
        if (courier.isEmpty()) {
            log.error("There is no courier with id = {}", courier);
            throw new NotFoundException("There is no courier with given id");
        }
        Set<ParcelEntity> parcels = parcelRepository.findAllByIdIn(deliveryCreateDomain.getParcelIds());
        if (parcels.isEmpty() || parcels.size() != deliveryCreateDomain.getParcelIds().size()) {
            log.error("There is no parcel with given ids = {}", deliveryCreateDomain.getClass());
            throw new NotFoundException("There is no parcels for given ids");
        }
        List<DeliveryEntity> deliveries = new ArrayList<>();
        parcels.forEach(p -> {
                    DeliveryEntity delivery = new DeliveryEntity();
                    delivery.setStartTime(LocalDateTime.now());
                    delivery.setParcel(p);
                    Optional<DeliveryStatusDomain> statusDomain = Optional.ofNullable(DeliveryStatusDomain.fromString(
                            deliveryCreateDomain.getStatus().name()));
                    delivery.setStatus(statusDomain.isPresent() ? DeliveryEntity.DeliveryStatus.fromString(
                            statusDomain.get().name()) : DeliveryEntity.DeliveryStatus.IN_PROGRESS);
                    String notes = deliveryCreateDomain.getNotes().isBlank() ? "" : deliveryCreateDomain.getNotes();
                    delivery.setNotes(notes);
                    courier.get().addDelivery(delivery);
                    deliveries.add(delivery);
                }
        );

        deliveryRepository.saveAll(deliveries);
    }
}
