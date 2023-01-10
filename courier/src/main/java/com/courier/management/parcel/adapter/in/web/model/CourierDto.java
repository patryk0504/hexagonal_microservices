package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CourierDto {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Email cannot be null")
    private String email;
    @NotBlank(message = "Phone cannot be null")
    private String phone;
    @NotBlank(message = "Vehicle cannot be null")
    private String vehicle;
}
