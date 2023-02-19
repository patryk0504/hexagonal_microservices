package com.route.adapter.in.web.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class GeoAddressDto {
    @NotBlank
    private double latitude;
    @NotBlank
    private double longitude;
    @NotBlank
    private String address;
}
