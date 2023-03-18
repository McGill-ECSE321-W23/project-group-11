package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class LargeTempSpot extends TempSpot {

   @Id
   @GeneratedValue
   private int id;

   @ManyToOne
   private Car car;
   
   private int placeNumber;

   public void setplaceNumber(int value) {
       this.placeNumber = value;
   }
 
   public int getplaceNumber() {
       return this.placeNumber;
   }

   public int getId() {
      return this.id;
   }

   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }
}