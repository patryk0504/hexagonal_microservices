package com.courier.management.parcel.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
