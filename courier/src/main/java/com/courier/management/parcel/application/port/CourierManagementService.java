package com.courier.management.parcel.application.port;

import com.courier.management.parcel.adapter.in.web.mapper.CourierDtoMapper;
import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.application.port.in.CreateCourierUseCase;
import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourierManagementService implements CreateCourierUseCase {

    private final CourierManagementWritePort writePort;
    private final CourierDtoMapper courierDtoMapper;

    @Transactional
    @Override
    public void createCourier(CourierDto courierDto) {
        writePort.createCourier(courierDtoMapper.toCourierDomain(courierDto));
    }
}
