package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.CourierEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.ParcelEntity;
import com.courier.management.parcel.adapter.out.persistence.mapper.CourierDomainMapper;
import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import com.courier.management.parcel.domain.CourierDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CourierManagementWriteAdapter implements CourierManagementWritePort {

    private final CourierRepository courierRepository;
    private final ParcelRepository parcelRepository;
    private final CourierDomainMapper courierDomainMapper;

    @Override
    public void createCourier(CourierDomain courierDomain) {
        CourierEntity courierEntity = courierDomainMapper.toCourierEntity(courierDomain);
        courierRepository.save(courierEntity);
    }

    @Override
    public void assignParcel(long courierId, List<Long> parcelIds) {
        CourierEntity courierEntity = courierRepository.findById(courierId).orElseThrow();
        Set<ParcelEntity> parcelEntities = parcelRepository.findAllByIdIn(parcelIds);

        parcelEntities.forEach(courierEntity::addParcel);
        courierRepository.save(courierEntity);
    }
}
