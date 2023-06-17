package com.courier.management.parcel.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parcel_id")
    private ParcelEntity parcel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private String notes;

    @OneToMany(
            mappedBy = "delivery",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RouteEntity> routes = new ArrayList<>();

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

    public void addRoute(RouteEntity route) {
        routes.add(route);
        route.setDelivery(this);
    }

    public void removeRoute(RouteEntity route) {
        routes.remove(route);
        route.setDelivery(null);
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
