package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ReservedSpot {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne
   private Car car;
   
   private int placeNumber;

   public Integer getId() {
      return this.id;
   }

   public void setplaceNumber(int value) {
       this.placeNumber = value;
   }
 
   public int getplaceNumber() {
       return this.placeNumber;
   }
   
   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }
}