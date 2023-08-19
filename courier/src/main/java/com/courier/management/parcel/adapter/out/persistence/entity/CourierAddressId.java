package com.courier.management.parcel.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourierAddressId implements Serializable {

    @Column(name = "courier_id")
    private Long courierId;

    @Column(name = "address_id")
    private Long addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CourierAddressId that = (CourierAddressId) o;
        return courierId != null && Objects.equals(courierId, that.courierId)
                && addressId != null && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courierId, addressId);
    }
}
