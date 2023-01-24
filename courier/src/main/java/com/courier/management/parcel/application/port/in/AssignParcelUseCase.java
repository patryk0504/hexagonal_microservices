package com.courier.management.parcel.application.port.in;

public interface AssignParcelUseCase {
    void assignParcelToCourier(long courierId, long parcelId);
    //TODO: maybe also good place place for assignParcel to User?
}
