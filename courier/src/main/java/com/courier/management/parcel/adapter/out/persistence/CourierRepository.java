package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.CourierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CourierRepository extends JpaRepository<CourierEntity, Long> {
    Optional<CourierEntity> findById(long id);

    Page<CourierEntity> findAll(Pageable pageable);
}
