package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RegularTempSpot extends TempSpot {

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