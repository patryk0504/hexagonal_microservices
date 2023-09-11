package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.mapper.CourierDomainMapperImpl;
import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.application.port.out.CourierManagementWritePort;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Import({CourierManagementReadAdapter.class, CourierDomainMapperImpl.class})
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CourierManagementReadAdapterTest {

    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private CourierManagementReadPort courierManagementReadPort;
    @Autowired
    private CourierManagementWritePort courierManagementWritePort;

    @Test
    public void testGetCouriers() {
        // given
        String sortOrder = "ASC";
        String sortBy = "name";
        int page = 0;
        int pageSize = 10;

        // when
        Page<CourierDomain> result = courierManagementReadPort.getCouriers(sortOrder, sortBy, page, pageSize);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void testGetCourier() {
        // given
        CourierDomain courierDomain = CourierDomain.builder().id(1L).name("test").vehicle("testVehicle")
                .email("test@test.test").build();
        // when
        courierManagementWritePort.createCourier(courierDomain);
        // then
        Optional<CourierDomain> result = courierManagementReadPort.getCourier(1L);
        assertThat(result).isNotEmpty();
        assertThat(result.get().getName()).isEqualTo("test");
    }
}
