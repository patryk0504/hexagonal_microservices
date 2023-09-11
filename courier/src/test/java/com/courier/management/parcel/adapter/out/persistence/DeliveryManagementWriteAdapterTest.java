package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.CourierDomainMockData;
import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.application.port.out.DeliveryManagementReadPort;
import com.courier.management.parcel.application.port.out.DeliveryManagementWritePort;
import com.courier.management.parcel.application.port.out.ParcelManagementReadPort;
import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.CourierAddressRoleEnumDomain;
import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.CourierShiftAddressDomain;
import com.courier.management.parcel.domain.DeliveryCreateDomain;
import com.courier.management.parcel.domain.DeliveryDomain;
import com.courier.management.parcel.domain.DeliveryStatusDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DeliveryManagementWriteAdapterTest {

    @Autowired
    private DeliveryManagementReadPort deliveryManagementReadPort;
    @Autowired
    private ParcelManagementReadPort parcelManagementReadPort;
    @Autowired
    private CourierManagementReadPort courierManagementReadPort;
    @Autowired
    private DeliveryManagementWritePort deliveryManagementWritePort;

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createDelivery() {
        // given
        DeliveryCreateDomain deliveryCreateDomain = DeliveryCreateDomain.builder().notes("notes").status(
                DeliveryStatusDomain.IN_PROGRESS).parcelIds(List.of(1L)).build();
        // when
        DeliveryDomain deliveryDomain = deliveryManagementWritePort.createDelivery(1L, deliveryCreateDomain, List.of());
        // then
        assertThat(deliveryDomain).isNotNull();
        assertThat(deliveryDomain.getNotes()).isEqualTo("notes");

        Set<ParcelDomain> parcelDomainSet = parcelManagementReadPort.getUnsignedParcels();
        assertThat(parcelDomainSet).isEmpty();

        Optional<CourierDomain> courierDomain = courierManagementReadPort.getCourier(1L);
        assertThat(courierDomain).isNotEmpty();
        assertThat(courierDomain.get().getDeliveries()).hasSize(2);
    }

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createDelivery_and_update_courier_shift_address() {
        // given
        DeliveryCreateDomain deliveryCreateDomain = DeliveryCreateDomain.builder().notes("notes").status(
                DeliveryStatusDomain.IN_PROGRESS).parcelIds(List.of(1L)).build();

        CourierShiftAddressDomain start = CourierShiftAddressDomain.builder()
                .shiftAddress(AddressDomain.builder().street("startAddress").geoAddress(
                        CourierDomainMockData.geoAddressDomainSender).build()).role(
                        CourierAddressRoleEnumDomain.START).build();
        CourierShiftAddressDomain end = CourierShiftAddressDomain.builder().shiftAddress(
                AddressDomain.builder().street("endAddress").geoAddress(CourierDomainMockData.geoAddressDomainSender)
                        .build()).role(
                CourierAddressRoleEnumDomain.END).build();
        // when
        DeliveryDomain deliveryDomain = deliveryManagementWritePort.createDelivery(1L, deliveryCreateDomain,
                List.of(start, end));
        // then
        assertThat(deliveryDomain).isNotNull();
        assertThat(deliveryDomain.getNotes()).isEqualTo("notes");

        Set<ParcelDomain> parcelDomainSet = parcelManagementReadPort.getUnsignedParcels();
        assertThat(parcelDomainSet).isEmpty();

        Optional<CourierDomain> courierDomain = courierManagementReadPort.getCourier(1L);
        assertThat(courierDomain).isNotEmpty();
        assertThat(courierDomain.get().getDeliveries()).hasSize(2);
    }
}