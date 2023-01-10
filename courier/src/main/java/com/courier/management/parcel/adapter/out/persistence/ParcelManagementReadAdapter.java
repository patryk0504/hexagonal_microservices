package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.ParcelManagementReadPort;
import com.courier.management.parcel.domain.ParcelDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParcelManagementReadAdapter implements ParcelManagementReadPort {

    private final ParcelRepository parcelRepository;
    private final ParcelDomainMapper parcelDomainMapper;

    @Override
    public Page<ParcelDomain> getParcels(String sortOrder, String sortBy, int page, int pageSize) {
        Sort.Direction direction = sortOrder != null && sortOrder.equalsIgnoreCase("ASC") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy != null ? sortBy : "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return parcelDomainMapper.toParcelDomainPage(parcelRepository.findAll(pageable));
    }
}
