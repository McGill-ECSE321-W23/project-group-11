package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class RegularTempSpot extends TempSpot {
   private float pricePer15;

   public void setPricePer15(float value) {
      this.pricePer15 = value;
   }

   public float getPricePer15() {
      return this.pricePer15;
   }

   private Car car;

   @OneToOne(mappedBy = "regularTempSpot")
   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }

   private ParkingManagementSystem parkingManagementSystem;

   @ManyToOne(optional = false)
   public ParkingManagementSystem getParkingManagementSystem() {
      return this.parkingManagementSystem;
   }

   public void setParkingManagementSystem(ParkingManagementSystem parkingManagementSystem) {
      this.parkingManagementSystem = parkingManagementSystem;
   }

}