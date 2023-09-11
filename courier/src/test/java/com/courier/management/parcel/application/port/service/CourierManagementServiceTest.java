package com.courier.management.parcel.application.port.service;

import com.courier.management.parcel.adapter.in.web.mapper.CourierDtoMapper;
import com.courier.management.parcel.adapter.in.web.mapper.ParcelDtoMapper;
import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import com.courier.management.parcel.application.port.out.ParcelManagementReadPort;
import com.courier.management.parcel.domain.CourierDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourierManagementServiceTest {

    private final CourierDtoMapper courierDtoMapper = Mappers.getMapper(CourierDtoMapper.class);
    private final ParcelDtoMapper parcelDtoMapper = Mappers.getMapper(ParcelDtoMapper.class);
    @Mock
    private CourierManagementReadPort readPort;
    @Mock
    private CourierManagementWritePort writePort;
    @Mock
    private ParcelManagementReadPort parcelManagementReadPort;
    private CourierManagementService courierManagementService;

    @BeforeEach
    public void setUp() {
        courierManagementService = new CourierManagementService(readPort, writePort, parcelManagementReadPort,
                courierDtoMapper, parcelDtoMapper);
    }

    @Test
    public void testCreateCourier() {
        // given
        CourierDto courierDto = new CourierDto();

        // when
        courierManagementService.createCourier(courierDto);

        // then
        verify(writePort, times(1)).createCourier(any(CourierDomain.class));
    }

    @Test
    void getParcelsForCourier() {
        // given
        Set<ParcelDto> expected = parcelDtoMapper.toParcelDtoSet(Set.of());
        when(parcelManagementReadPort.getParcelsForCourier(eq(1L))).thenReturn(Set.of());
        // when
        Set<ParcelDto> result = courierManagementService.getParcelsForCourier(1L);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getParcelsForCourierByStatus() {
        // given
        Set<ParcelDto> expected = parcelDtoMapper.toParcelDtoSet(Set.of());
        when(parcelManagementReadPort.getParcelsForCourierByStatus(anyLong(), any())).thenReturn(Set.of());
        // when
        Set<ParcelDto> result = courierManagementService.getParcelsForCourierByStatus(1L, "CREATED");
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getParcelsForCourierByStatus_when_status_is_invalid() {
        // given
        Set<ParcelDto> expected = parcelDtoMapper.toParcelDtoSet(Set.of());
        // when
        Set<ParcelDto> result = courierManagementService.getParcelsForCourierByStatus(1L, "invalid");
        // then
        assertThat(result).isEqualTo(expected);
        verify(parcelManagementReadPort, times(0)).getParcelsForCourierByStatus(anyLong(), any());
    }

    @Test
    void assignParcelToCourier() {
        // given
        doNothing().when(writePort).assignParcel(anyLong(), anyList());
        // when
        // then
        courierManagementService.assignParcelToCourier(1L, List.of(1L));
    }

    @Test
    void getCouriers() {
        // given
        when(readPort.getCouriers(anyString(), anyString(), anyInt(), anyInt())).thenReturn(Page.empty());
        // when
        Page<CourierDto> result = courierManagementService.getCouriers("ASC", "id", 1, 1);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    void getCourier() {
        // given
        when(readPort.getCourier(anyLong())).thenReturn(Optional.empty());
        // when
        CourierDto result = courierManagementService.getCourier(1L);
        // then
        assertThat(result).isNull();
    }
}
