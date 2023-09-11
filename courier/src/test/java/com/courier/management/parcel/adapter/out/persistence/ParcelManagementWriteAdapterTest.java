package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.CourierDomainMockData;
import com.courier.management.parcel.application.port.out.ParcelManagementReadPort;
import com.courier.management.parcel.application.port.out.ParcelManagementWritePort;
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

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ParcelManagementWriteAdapterTest {

    @Autowired
    private ParcelManagementReadPort parcelManagementReadPort;
    @Autowired
    private ParcelManagementWritePort parcelManagementWritePort;

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void createParcel() {
        // given
        ParcelDomain parcelDomain = ParcelDomain.builder().name("testParcel")
                .users(Set.of(CourierDomainMockData.userParcelDomainSender,
                        CourierDomainMockData.userParcelDomainRecipient)).address(
                        Set.of(CourierDomainMockData.parcelAddressDomainSender,
                                CourierDomainMockData.parcelAddressDomainRecipient)).build();
        // when
        parcelManagementWritePort.createParcel(parcelDomain);
        // then
        Set<ParcelDomain> parcelDomains = parcelManagementReadPort.getUnsignedParcels();
        assertThat(parcelDomains).hasSize(1);
    }
}