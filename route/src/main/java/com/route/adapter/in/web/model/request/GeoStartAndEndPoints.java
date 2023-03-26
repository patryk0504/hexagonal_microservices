package com.route.adapter.in.web.model.request;

import com.route.adapter.in.web.model.model.AddressDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class GeoStartAndEndPoints {
    @NotBlank(message = "Start point cannot be null")
    AddressDto startPoint;
    @NotBlank(message = "End point cannot be null")
    AddressDto endPoint;
}
