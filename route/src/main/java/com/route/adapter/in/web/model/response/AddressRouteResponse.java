package com.route.adapter.in.web.model.response;

import lombok.Data;

import java.util.List;

@Data
public class AddressRouteResponse {
    List<Address> addressRoute;

    @Data
    public static class Address {
        double latitude;
        double longitude;
    }
}
