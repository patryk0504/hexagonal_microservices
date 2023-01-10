package com.courier.management.parcel.application.port;

import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.application.port.in.CreateCourierUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourierManagementService implements CreateCourierUseCase {

    @Transactional
    @Override
    public void createCourier(CourierDto courierDto) {

    }
}
