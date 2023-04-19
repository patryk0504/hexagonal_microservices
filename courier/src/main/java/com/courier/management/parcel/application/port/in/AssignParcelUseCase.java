package com.courier.management.parcel.application.port.in;

import java.util.List;

public interface AssignParcelUseCase {
    void assignParcelToCourier(long courierId, List<Long> parcelIds);
    //TODO: maybe also good place place for assignParcel to User?
}
