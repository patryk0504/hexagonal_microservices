package com.courier.management.parcel.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "parcel_table")
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ParcelStatus status = ParcelStatus.CREATED;

    private BigDecimal weight;
    private String dimensions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;


    @OneToMany(
            mappedBy = "parcel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserParcelEntity> users = new HashSet<>();

    @OneToMany(
            mappedBy = "parcel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ParcelAddressEntity> address = new HashSet<>();

    enum ParcelStatus {
        CREATED, IN_TRANSIT, DELIVERED, RETURNED
    }

    public void addAddress(AddressEntity address, RoleEnum role) {
        ParcelAddressEntity userParcelEntity = new ParcelAddressEntity(this, address, role);
        this.address.add(userParcelEntity);
        address.getParcel().add(userParcelEntity);
    }

    public void removeAddress(AddressEntity address) {
        for (Iterator<ParcelAddressEntity> iterator = this.address.iterator();
             iterator.hasNext(); ) {
            ParcelAddressEntity parcelAddress = iterator.next();

            if (parcelAddress.getParcel().equals(this) &&
                    parcelAddress.getAddress().equals(address)) {
                iterator.remove();
                parcelAddress.getAddress().getParcel().remove(parcelAddress);
                parcelAddress.setAddress(null);
                parcelAddress.setParcel(null);
            }
        }
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
