package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import com.courier.management.parcel.domain.CourierDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void assignParcel(long courierId, long parcelId) {
        CourierEntity courierEntity = courierRepository.findById(courierId).orElseThrow();
        ParcelEntity parcelEntity = parcelRepository.findById(parcelId).orElseThrow();

        courierEntity.addParcel(parcelEntity);
        courierRepository.save(courierEntity);
    }
}
