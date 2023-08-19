package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
    Optional<DeliveryEntity> findFirstByCourierIdOrderByCreatedOnAsc(long courierId);
}
