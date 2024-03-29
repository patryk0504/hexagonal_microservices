package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUser(long userId);
}
