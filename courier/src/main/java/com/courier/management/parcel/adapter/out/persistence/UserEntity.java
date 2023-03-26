package com.courier.management.parcel.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_table")
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String email;
    String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserParcelEntity> parcels = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AddressEntity> address = new HashSet<>();

    public void addParcel(ParcelEntity parcel) {
        UserParcelEntity userParcelEntity = new UserParcelEntity(this, parcel);
        parcels.add(userParcelEntity);
        parcel.getUsers().add(userParcelEntity);
    }

    public void removeParcel(ParcelEntity parcel) {
        for (Iterator<UserParcelEntity> iterator = parcels.iterator();
             iterator.hasNext(); ) {
            UserParcelEntity userParcel = iterator.next();

            if (userParcel.getUser().equals(this) &&
                    userParcel.getParcel().equals(parcel)) {
                iterator.remove();
                userParcel.getUser().getParcels().remove(userParcel);
                userParcel.setUser(null);
                userParcel.setParcel(null);
            }
        }
    }

    public void addAddress(AddressEntity address) {
        this.address.add(address);
        address.setUser(this);
    }

    public void removeAddress(AddressEntity address) {
        this.address.remove(address);
        address.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
