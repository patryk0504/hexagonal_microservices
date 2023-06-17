package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
    Set<DeliveryEntity> findAllByCourierId(long courierId);
}
