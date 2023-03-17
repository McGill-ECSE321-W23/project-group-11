package ca.mcgill.ecse321.ParkingManagement.model;

import java.io.Serializable;


public class SpecificServiceId implements Serializable {

   private ServiceType serviceType;
   private Car car;
 
   public ServiceType getServiceType() {
      return serviceType;
   }

   public void setServiceType(ServiceType serviceType) {
      this.serviceType = serviceType;
   }

   public Car getCar() {
      return car;
   }

   public void setCar(Car car) {
      this.car = car;
   }
}