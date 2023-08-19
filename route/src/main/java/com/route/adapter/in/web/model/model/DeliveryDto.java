package com.route.adapter.in.web.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryDto {
    private Long id;
    @NotBlank(message = "Name cannot be null")
    private List<Long> parcelIds;
    private DeliveryStatus status;
    private String notes;
    private Long courierId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
