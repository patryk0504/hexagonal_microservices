package com.courier.management.parcel.adapter.out.persistence;

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
@Table(name = "user_parcel_table")
public class UserParcelEntity {
    @EmbeddedId
    private UserParcelId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("parcelId")
    private ParcelEntity parcel;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UserParcelEntity(UserEntity user, ParcelEntity parcel) {
        this.user = user;
        this.parcel = parcel;
        this.id = new UserParcelId(user.getId(), parcel.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserParcelEntity that = (UserParcelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
