package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import com.courier.management.parcel.domain.CourierDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourierManagementWriteAdapter implements CourierManagementWritePort {

    private final CourierRepository courierRepository;
    private final CourierDomainMapper courierDomainMapper;

    @Override
    public void createCourier(CourierDomain courierDomain) {
        CourierEntity courierEntity = courierDomainMapper.toCourierEntity(courierDomain);
    }
}
