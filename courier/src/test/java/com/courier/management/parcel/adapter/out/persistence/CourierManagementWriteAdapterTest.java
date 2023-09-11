package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.mapper.CourierDomainMapperImpl;
import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
import com.courier.management.parcel.application.port.out.ParcelManagementWritePort;
import com.courier.management.parcel.application.port.out.UserManagementWritePort;
import com.courier.management.parcel.domain.CourierDomain;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Import({CourierManagementReadAdapter.class, CourierDomainMapperImpl.class})
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CourierManagementWriteAdapterTest {

    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private CourierManagementReadPort courierManagementReadPort;
    @Autowired
    private CourierManagementWritePort courierManagementWritePort;
    @Autowired
    private ParcelManagementWritePort parcelManagementWritePort;
    @Autowired
    private UserManagementWritePort userManagementWritePort;


    @Test
    void createCourier() {
        // given
        CourierDomain courierDomain = CourierDomain.builder().name("test").vehicle("testVehicle")
                .email("test@test.test").build();
        // when
        courierManagementWritePort.createCourier(courierDomain);
        // then
        Page<CourierDomain> result = courierManagementReadPort.getCouriers("ASC", "id", 0, 10);
        assertThat(result.getContent()).usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(List.of(courierDomain));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void assignParcel() {
        // given
        CourierDomain courierDomain = CourierDomain.builder().name("test").vehicle("testVehicle")
                .email("test@test.test").build();
        courierManagementWritePort.createCourier(courierDomain);
        // when
        courierManagementWritePort.assignParcel(3L, List.of(2L));
        // then
        Optional<CourierDomain> result = courierManagementReadPort.getCourier(3L);
        assertThat(result).isNotEmpty();
        assertThat(result.get().getParcels()).isNotEmpty();
    }
}