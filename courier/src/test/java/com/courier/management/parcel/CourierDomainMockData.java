package com.courier.management.parcel;

import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.CourierStatusDomain;
import com.courier.management.parcel.domain.DeliveryDomain;
import com.courier.management.parcel.domain.DeliveryStatusDomain;
import com.courier.management.parcel.domain.GeoAddressDomain;
import com.courier.management.parcel.domain.ParcelAddressDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import com.courier.management.parcel.domain.ParcelStatusDomain;
import com.courier.management.parcel.domain.RoleEnumDomain;
import com.courier.management.parcel.domain.UserParcelDomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class CourierDomainMockData {
    public static DeliveryDomain deliveryDomain1 = DeliveryDomain.builder().id(1L).parcels(List.of(1L, 2L))
            .courierId(1L).startTime(LocalDateTime.parse("2023-03-30T12:15"))
            .endTime(LocalDateTime.parse("2023-03-30T13:00")).status(DeliveryStatusDomain.IN_PROGRESS)
            .notes("Delivered to the front desk").deliveryOrder(1).build();

    // user
    public static UserParcelDomain userParcelDomainSender = UserParcelDomain.builder().user(1L)
            .role(RoleEnumDomain.SENDER).build();
    public static UserParcelDomain userParcelDomainRecipient = UserParcelDomain.builder().user(2L)
            .role(RoleEnumDomain.RECIPIENT).build();

    // sender
    public static GeoAddressDomain geoAddressDomainSender = GeoAddressDomain.builder().latitude(50.0620054)
            .longitude(19.9409846).build();
    public static AddressDomain addressDomainSender = AddressDomain.builder().id(1L).street("Karmelicka 27")
            .city("Kraków").state("Małopolskie").postalCode("31-000").country("Poland")
            .geoAddress(geoAddressDomainSender).build();

    public static ParcelAddressDomain parcelAddressDomainSender = ParcelAddressDomain.builder()
            .address(addressDomainSender).role(RoleEnumDomain.SENDER).build();

    // recipient
    public static GeoAddressDomain geoAddressDomainRecipient = GeoAddressDomain.builder().latitude(50.0496831)
            .longitude(19.9458382).build();
    public static AddressDomain addressDomainRecipient = AddressDomain.builder().id(2L).street("Długa 5").city("Kraków")
            .state("Małopolskie").postalCode("31-039").country("Poland").geoAddress(geoAddressDomainRecipient).build();

    public static ParcelAddressDomain parcelAddressDomainRecipient = ParcelAddressDomain.builder()
            .address(addressDomainRecipient).role(RoleEnumDomain.RECIPIENT).build();
    public static ParcelDomain parcelDomain1 = ParcelDomain.builder().id(1L).name("Small package")
            .address(Set.of(parcelAddressDomainRecipient, parcelAddressDomainSender))
            .users(Set.of(userParcelDomainSender, userParcelDomainRecipient)).weight(BigDecimal.valueOf(1.5))
            .dimensions("20x10x5").status(ParcelStatusDomain.CREATED).build();
    public static CourierDomain courierDomain = CourierDomain.builder().id(1L).name("John Doe")
            .email("john@example.com").phone("+48 123 456 789").vehicle("bike").deliveries(Set.of(deliveryDomain1))
            .parcels(Set.of(parcelDomain1)).status(CourierStatusDomain.AVAILABLE).shiftAddress(Set.of()).build();
}
