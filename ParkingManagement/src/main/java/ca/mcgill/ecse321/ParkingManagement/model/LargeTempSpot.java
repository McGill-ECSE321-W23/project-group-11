package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class LargeTempSpot extends TempSpot {

   @Id
   private int id;

   public void setId(int value) {
      this.id = value;
   }

   public int getId() {
      return this.id;
   }

   @ManyToOne
   private Car car;

   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }
}