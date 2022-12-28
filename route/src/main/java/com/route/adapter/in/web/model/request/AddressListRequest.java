package com.route.adapter.in.web.model.request;

import lombok.Data;

import java.util.List;

@Data
public class AddressListRequest {
    List<Address> addressList;

    @Data
    public static class Address {
        double latitude;
        double longitude;
    }
}
