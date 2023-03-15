package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RegularTempSpot extends TempSpot {

   @Id
   private int id;

   public void setId(int value) {
      this.id = value;
   }

   public int getId() {
      return this.id;
   }

   
   private Car car;

   @ManyToOne
   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }
}