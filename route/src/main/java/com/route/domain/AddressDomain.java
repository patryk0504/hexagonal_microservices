package com.route.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressDomain {
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
