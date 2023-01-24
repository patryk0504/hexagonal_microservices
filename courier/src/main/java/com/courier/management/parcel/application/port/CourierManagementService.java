package com.courier.management.parcel.application.port;

import com.courier.management.parcel.adapter.in.web.mapper.CourierDtoMapper;
import com.courier.management.parcel.adapter.in.web.mapper.ParcelDtoMapper;
import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.AssignParcelUseCase;
import com.courier.management.parcel.application.port.in.CreateCourierUseCase;
import com.courier.management.parcel.application.port.in.GetCouriersUseCase;
import com.courier.management.parcel.application.port.in.GetParcelForCourierUseCase;
import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourierManagementService implements CreateCourierUseCase, GetParcelForCourierUseCase, AssignParcelUseCase, GetCouriersUseCase {

    private final CourierManagementReadPort readPort;
    private final CourierManagementWritePort writePort;
    private final CourierDtoMapper courierDtoMapper;
    private final ParcelDtoMapper parcelDtoMapper;


    @Transactional
    @Override
    public void createCourier(CourierDto courierDto) {
        writePort.createCourier(courierDtoMapper.toCourierDomain(courierDto));
    }

    @Transactional(readOnly = true)
    @Override
    public Set<ParcelDto> getParcelsForCourier(long courierId) {
        Set<ParcelDomain> parcelDomains = readPort.getParcelsForCourier(courierId);
        return parcelDtoMapper.toParcelDtoSet(parcelDomains);
    }

    @Transactional
    @Override
    public void assignParcelToCourier(long courierId, long parcelId) {
        writePort.assignParcel(courierId, parcelId);
    }

    @Override
    public Page<CourierDto> getCouriers(String sortOrder, String sortBy, int page, int pageSize) {
        Page<CourierDomain> courierDomains = readPort.getCouriers(sortOrder, sortBy, page, pageSize);
        return courierDtoMapper.toCourierDtoPage(courierDomains);
    }
}
