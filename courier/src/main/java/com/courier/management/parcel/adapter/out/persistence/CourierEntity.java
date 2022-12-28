package com.courier.management.parcel.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courier")
class CourierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String currentLocation;
    private String vehicle;

    @OneToMany(
            mappedBy = "parcel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<DeliveryEntity> deliveries = new HashSet<>();

    @OneToMany(
            mappedBy = "courier",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ParcelEntity> parcels = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private CourierStatus status;


    public void addDelivery(DeliveryEntity delivery) {
        deliveries.add(delivery);
        delivery.setCourier(this);
    }

    public void removeDelivery(DeliveryEntity delivery) {
        deliveries.remove(delivery);
        delivery.setCourier(null);
    }

    public void addParcel(ParcelEntity parcel) {
        parcels.add(parcel);
        parcel.setCourier(this);
    }

    public void removeParcel(ParcelEntity parcel) {
        parcels.remove(parcel);
        parcel.setCourier(null);
    }

    enum CourierStatus {
        AVAILABLE, ON_DELIVERY, OFF_DUTY
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CourierEntity that = (CourierEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
