package com.courier.management.parcel.application.port.service;

import com.courier.management.parcel.adapter.in.web.mapper.CourierDtoMapper;
import com.courier.management.parcel.adapter.in.web.mapper.ParcelDtoMapper;
import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.AssignParcelUseCase;
import com.courier.management.parcel.application.port.in.CourierCrudOperations;
import com.courier.management.parcel.application.port.in.GetParcelForCourierUseCase;
import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import com.courier.management.parcel.application.port.out.ParcelManagementReadPort;
import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import com.courier.management.parcel.domain.ParcelStatusDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CourierManagementService implements CourierCrudOperations, GetParcelForCourierUseCase, AssignParcelUseCase {

    private final CourierManagementReadPort readPort;
    private final CourierManagementWritePort writePort;
    private final ParcelManagementReadPort parcelManagementReadPort;
    private final CourierDtoMapper courierDtoMapper;
    private final ParcelDtoMapper parcelDtoMapper;


    @Override
    public void createCourier(CourierDto courierDto) {
        writePort.createCourier(courierDtoMapper.toCourierDomain(courierDto));
    }

    @Transactional(readOnly = true)
    @Override
    public Set<ParcelDto> getParcelsForCourier(long courierId) {
        Set<ParcelDomain> parcelDomains = parcelManagementReadPort.getParcelsForCourier(courierId);
        return parcelDtoMapper.toParcelDtoSet(parcelDomains);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<ParcelDto> getParcelsForCourierByStatus(long courierId, String status) {
        Optional<ParcelStatusDomain> parcelStatusDomain = Optional.ofNullable(ParcelStatusDomain.fromString(status));

        if (parcelStatusDomain.isPresent()) {
            Set<ParcelDomain> parcelDomains = parcelManagementReadPort.getParcelsForCourierByStatus(courierId,
                    parcelStatusDomain.get());
            return parcelDtoMapper.toParcelDtoSet(parcelDomains);
        }

        return Collections.emptySet();
    }

    @Override
    public void assignParcelToCourier(long courierId, List<Long> parcelIds) {
        writePort.assignParcel(courierId, parcelIds);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CourierDto> getCouriers(String sortOrder, String sortBy, int page, int pageSize) {
        Page<CourierDomain> courierDomains = readPort.getCouriers(sortOrder, sortBy, page, pageSize);
        return courierDtoMapper.toCourierDtoPage(courierDomains);
    }

    @Override
    public CourierDto getCourier(long courierId) {
        Optional<CourierDomain> courierDomain = readPort.getCourier(courierId);
        return courierDomain.map(courierDtoMapper::toCourierDto).orElse(null);
    }
}
