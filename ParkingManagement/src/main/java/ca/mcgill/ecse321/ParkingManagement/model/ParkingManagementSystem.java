package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/
//TODO do we need jtags for this?

import jakarta.persistence.Entity;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;


@Entity
public class ParkingManagementSystem{
   private int id;

public void setId(int value) {
    this.id = value;
}
public int getId() {
    return this.id;
}
   private Set<Account> account;
   
   @OneToMany(mappedBy="parkingManagementSystem" , cascade={CascadeType.ALL})
   public Set<Account> getAccount() {
      return this.account;
   }
   
   public void setAccount(Set<Account> accounts) {
      this.account = accounts;
   }
   
   private Set<Car> car;
   
   @OneToMany(mappedBy="parkingManagementSystem" , cascade={CascadeType.ALL})
   public Set<Car> getCar() {
      return this.car;
   }
   
   public void setCar(Set<Car> cars) {
      this.car = cars;
   }
   
   private Set<ReservedSpot> reservedSpot;
   
   @OneToMany(mappedBy="parkingManagementSystem" , cascade={CascadeType.ALL})
   public Set<ReservedSpot> getReservedSpot() {
      return this.reservedSpot;
   }
   
   public void setReservedSpot(Set<ReservedSpot> reservedSpots) {
      this.reservedSpot = reservedSpots;
   }
   
   private Set<RegularTempSpot> regularTempSpot;
   
   @OneToMany(mappedBy="parkingManagementSystem" , cascade={CascadeType.ALL})
   public Set<RegularTempSpot> getRegularTempSpot() {
      return this.regularTempSpot;
   }
   
   public void setRegularTempSpot(Set<RegularTempSpot> regularTempSpots) {
      this.regularTempSpot = regularTempSpots;
   }
   
   private Set<LargeTempSpot> largeTempSpot;
   
   @OneToMany(mappedBy="parkingManagementSystem" , cascade={CascadeType.ALL})
   public Set<LargeTempSpot> getLargeTempSpot() {
      return this.largeTempSpot;
   }
   
   public void setLargeTempSpot(Set<LargeTempSpot> largeTempSpots) {
      this.largeTempSpot = largeTempSpots;
   }
   
   private Set<SpecificService> specificService;
   
   @OneToMany(mappedBy="parkingManagementSystem" , cascade={CascadeType.ALL})
   public Set<SpecificService> getSpecificService() {
      return this.specificService;
   }
   
   public void setSpecificService(Set<SpecificService> specificServices) {
      this.specificService = specificServices;
   }
   
   private Set<ServiceType> serviceType;
   
   @OneToMany(mappedBy="parkingManagementSystem" , cascade={CascadeType.ALL})
   public Set<ServiceType> getServiceType() {
      return this.serviceType;
   }
   
   public void setServiceType(Set<ServiceType> serviceTypes) {
      this.serviceType = serviceTypes;
   }
   
   }
