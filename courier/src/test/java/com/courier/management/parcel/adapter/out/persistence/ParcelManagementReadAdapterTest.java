package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.ParcelManagementReadPort;
import com.courier.management.parcel.application.port.out.ParcelManagementWritePort;
import com.courier.management.parcel.domain.ParcelDomain;
import com.courier.management.parcel.domain.ParcelStatusDomain;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ParcelManagementReadAdapterTest {

    @Autowired
    private ParcelManagementReadPort parcelManagementReadPort;
    @Autowired
    private ParcelManagementWritePort parcelManagementWritePort;

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getParcels() {
        // given
        // when
        Page<ParcelDomain> result = parcelManagementReadPort.getParcels("ASC", null, 0, 10);
        // then
        assertThat(result).hasSize(2);
    }

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getParcelsForCourier() {
        // given
        // when
        Set<ParcelDomain> result = parcelManagementReadPort.getParcelsForCourier(1L);
        // then
        assertThat(result).hasSize(1);
    }

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getParcelsForCourierByStatus() {
        // given
        // when
        Set<ParcelDomain> result = parcelManagementReadPort.getParcelsForCourierByStatus(1L,
                ParcelStatusDomain.CREATED);
        // then
        assertThat(result).hasSize(1);
    }

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getUnsignedParcels() {
        // given
        // when
        Set<ParcelDomain> result = parcelManagementReadPort.getUnsignedParcels();
        // then
        assertThat(result).isEmpty();
    }
}