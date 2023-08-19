package com.route.domain;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class AddressDomain {
    private Long parcelId;
    private double latitude;
    private double longitude;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String simpleAddress;

    public String getFormattedAddress() {
        return StringUtils.isAllBlank(street, city, state, postalCode,
                country) ? this.getSimpleAddress() : String.format("%s, %s, %s, %s, %s", street, city, postalCode,
                state, country);
    }
}
