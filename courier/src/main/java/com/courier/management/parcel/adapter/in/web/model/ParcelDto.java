package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ParcelDto {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Weight cannot be null")
    private String weight;
    @NotBlank(message = "Dimensions cannot be null")
    private String dimensions;
    @NotBlank(message = "senderName cannot be null")
    private String senderName;
    @NotBlank(message = "senderAddress cannot be null")
    private String senderAddress;
    @NotBlank(message = "recipientName cannot be null")
    private String recipientName;
    @NotBlank(message = "recipientAddress cannot be null")
    private String recipientAddress;
    @NotNull(message = "recipientGeoAddress cannot be null")
    private GeoAddressDto recipientGeoAddress;
    private String status;
}
