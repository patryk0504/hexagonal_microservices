package com.courier.management.parcel.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parcel_address_table")
public class ParcelAddressEntity {
    @EmbeddedId
    private ParcelAddressId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("parcelId")
    private ParcelEntity parcel;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("addressId")
    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public ParcelAddressEntity(ParcelEntity parcel, AddressEntity address, RoleEnum role) {
        this.parcel = parcel;
        this.address = address;
        this.id = new ParcelAddressId(parcel.getId(), address.getId());
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParcelAddressEntity that = (ParcelAddressEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
