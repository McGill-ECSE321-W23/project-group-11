package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import ca.mcgill.ecse321.parkingmanagement.model.ParkingManagement;
import javax.persistence.ManyToOne;

@Entity
public class LargeTempSpot extends TempSpot{
   private float pricePer15;

public void setPricePer15(float value) {
    this.pricePer15 = value;
}
public float getPricePer15() {
    return this.pricePer15;
}
   private Car car;
   
   @OneToOne(mappedBy="largeTempSpot" )
   public Car getCar() {
      return this.car;
   }
   
   public void setCar(Car car) {
      this.car = car;
   }
   
   private ParkingManagement parkingManagementSystem;
   
   @ManyToOne(optional=false)
   public ParkingManagement getParkingManagementSystem() {
      return this.parkingManagementSystem;
   }
   
   public void setParkingManagementSystem(ParkingManagement parkingManagementSystem) {
      this.parkingManagementSystem = parkingManagementSystem;
   }
   
   }
