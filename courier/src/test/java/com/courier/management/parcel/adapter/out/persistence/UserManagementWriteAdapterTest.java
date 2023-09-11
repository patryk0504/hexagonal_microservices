package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.CourierDomainMockData;
import com.courier.management.parcel.application.port.out.UserManagementReadPort;
import com.courier.management.parcel.application.port.out.UserManagementWritePort;
import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.UserDomain;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserManagementWriteAdapterTest {

    @Autowired
    private UserManagementReadPort userManagementReadPort;

    @Autowired
    private UserManagementWritePort userManagementWritePort;

    @Test
    @Sql(scripts = "classpath:data3.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void addUserAddress() {
        // given
        AddressDomain addressDomain = AddressDomain.builder().street("test")
                .geoAddress(CourierDomainMockData.geoAddressDomainSender).build();
        // when
        userManagementWritePort.addUserAddress(1L, addressDomain);
        // then
        List<AddressDomain> addressDomains = userManagementReadPort.getUserAddresses(1L);
        assertThat(addressDomains).hasSize(2);
    }

    @Test
    void addUser() {
        // given
        UserDomain userDomain = UserDomain.builder().name("test").email("test").build();
        // when
        userManagementWritePort.addUser(userDomain);
        // then
        List<UserDomain> userDomains = userManagementReadPort.getUsers();
        assertThat(userDomains).hasSize(1);
        assertThat(userDomains.get(0).getName()).isEqualTo("test");
    }
}