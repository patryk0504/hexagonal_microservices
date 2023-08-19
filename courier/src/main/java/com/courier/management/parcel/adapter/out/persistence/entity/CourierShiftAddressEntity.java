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
@Table(name = "courier_shift_address_table")
public class CourierShiftAddressEntity {
    @EmbeddedId
    private CourierAddressId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courierId")
    private CourierEntity courier;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("addressId")
    private ShiftAddressEntity address;

    @Enumerated(EnumType.STRING)
    private CourierAddressRoleEnum role;

    public CourierShiftAddressEntity(CourierEntity courier, ShiftAddressEntity address, CourierAddressRoleEnum role) {
        this.courier = courier;
        this.address = address;
        this.id = new CourierAddressId(courier.getId(), address.getId());
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CourierShiftAddressEntity that = (CourierShiftAddressEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
