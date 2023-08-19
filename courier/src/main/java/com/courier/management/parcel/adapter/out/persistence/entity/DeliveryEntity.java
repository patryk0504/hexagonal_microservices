package com.courier.management.parcel.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

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
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "delivery_table")
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "delivery",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ParcelEntity> parcels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private String notes;
    private int deliveryOrder;

    @CreationTimestamp
    private Instant createdOn;

    public enum DeliveryStatus {
        IN_PROGRESS, COMPLETED;

        public static DeliveryStatus fromString(String text) {
            for (DeliveryStatus b : DeliveryStatus.values()) {
                if (b.name().equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public void addParcel(ParcelEntity parcel) {
        this.getParcels().add(parcel);
        parcel.setDelivery(this);
        this.deliveryOrder = this.deliveryOrder + 1;
//        parcel.setDeliveryOrder(parcel.getDeliveryOrder() + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeliveryEntity that = (DeliveryEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
