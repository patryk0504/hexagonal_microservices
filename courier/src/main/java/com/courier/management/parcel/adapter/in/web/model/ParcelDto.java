package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
public class ParcelDto {
    Long id;
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Weight cannot be null")
    private String weight;
    @NotBlank(message = "Dimensions cannot be null")
    private String dimensions;
    private String status;
    private Set<ParcelAddressDto> address;
    private Set<UserParcelDto> users;
}
