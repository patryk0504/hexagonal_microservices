package com.courier.management.parcel.adapter.out.persistence;

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
public class UserParcelId
        implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "parcel_id")
    private Long parcelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserParcelId that = (UserParcelId) o;
        return userId != null && Objects.equals(userId, that.userId)
                && parcelId != null && Objects.equals(parcelId, that.parcelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, parcelId);
    }
}