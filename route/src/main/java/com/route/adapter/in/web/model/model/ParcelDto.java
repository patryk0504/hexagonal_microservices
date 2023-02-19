package com.route.adapter.in.web.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParcelDto {
    private String name;
    private String weight;
    private String dimensions;
    private String senderName;
    private String senderAddress;
    private String recipientName;
    private String recipientAddress;
    private AddressDto recipientGeoAddress;
}

