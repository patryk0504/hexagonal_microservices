package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.DeliveryManagementReadPort;
import com.courier.management.parcel.domain.DeliveryDomain;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DeliveryManagementReadAdapterTest {

    @Autowired
    private DeliveryManagementReadPort deliveryManagementReadPort;


    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getCourierDeliveries() {
        // given
        // when
        Optional<DeliveryDomain> result = deliveryManagementReadPort.getCourierDeliveries(1L);
        // then
        assertThat(result).isNotEmpty();
    }
}