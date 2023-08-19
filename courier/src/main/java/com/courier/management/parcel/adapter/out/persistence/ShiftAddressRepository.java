package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.ShiftAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface ShiftAddressRepository extends JpaRepository<ShiftAddressEntity, Long> {
}
