package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.ParcelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    Page<ParcelEntity> findAll(Pageable pageable);

    Set<ParcelEntity> findAllByIdIn(List<Long> ids);

    Optional<ParcelEntity> findById(long id);

    Set<ParcelEntity> findAllByCourierIdAndStatus(@Param("id") long id, @Param("status") ParcelEntity.ParcelStatus status);

    Set<ParcelEntity> findAllByCourierId(long id);

    Set<ParcelEntity> findAllByCourierIsNull();
}

