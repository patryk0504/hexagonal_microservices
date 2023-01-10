package com.courier.management.parcel.application.port;

import com.courier.management.parcel.adapter.in.web.mapper.ParcelDtoMapper;
import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.CreateParcelUseCase;
import com.courier.management.parcel.application.port.in.GetParcelsUseCase;
import com.courier.management.parcel.application.port.out.ParcelManagementReadPort;
import com.courier.management.parcel.application.port.out.ParcelManagementWritePort;
import com.courier.management.parcel.domain.ParcelDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ParcelManagementService implements CreateParcelUseCase, GetParcelsUseCase {

    private final ParcelManagementWritePort parcelManagementWritePort;
    private final ParcelManagementReadPort parcelManagementReadPort;
    private final ParcelDtoMapper parcelDtoMapper;

    @Transactional
    @Override
    public void createParcel(ParcelDto parcelDto) {
        parcelManagementWritePort.createParcel(parcelDtoMapper.toParcelDomain(parcelDto));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ParcelDto> getParcels(String sortOrder, String sortBy, int page, int pageSize) {
        Page<ParcelDomain> parcelDomains = parcelManagementReadPort.getParcels(sortOrder, sortBy, page, pageSize);
        return parcelDtoMapper.toParcelDtoPage(parcelDomains);
    }
}
