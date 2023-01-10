package com.courier.management.parcel.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    Page<ParcelEntity> findAll(Pageable pageable);
}
