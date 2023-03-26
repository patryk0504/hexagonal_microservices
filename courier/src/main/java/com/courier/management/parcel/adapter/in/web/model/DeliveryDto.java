package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryDto {
    @NotBlank(message = "Name cannot be null")
    List<Long> parcelIds;
    private DeliveryStatus status;
    private String notes;
}
