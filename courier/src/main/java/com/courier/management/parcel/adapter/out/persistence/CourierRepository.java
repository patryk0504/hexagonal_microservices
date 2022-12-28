package com.courier.management.parcel.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface CourierRepository extends JpaRepository<CourierEntity, Long> {
}
