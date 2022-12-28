package com.courier.management.parcel.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "parcel")
class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderName;
    private String senderAddress;
    private String recipientName;
    private String recipientAddress;
    private GeoAddress recipientGeoAddress;
    private LocalDate deliveryDate;
    private LocalTime deliveryTime;

    @Enumerated(EnumType.STRING)
    private PackageStatus status;

    private BigDecimal weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    enum PackageStatus {
        IN_TRANSIT, DELIVERED, RETURNED
    }


    @Embeddable
    @Getter
    @Setter
    static class GeoAddress {
        private double latitude;
        private double longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParcelEntity that = (ParcelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
