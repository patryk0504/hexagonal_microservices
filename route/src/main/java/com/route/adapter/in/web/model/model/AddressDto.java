package com.route.adapter.in.web.model.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressDto {
    @NotBlank
    double latitude;
    @NotBlank
    double longitude;
    @NotBlank
    String address;
}