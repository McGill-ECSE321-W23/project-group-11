package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ReservedSpot {

   private Integer id;

   public void setId(Integer value) {
      this.id = value;
   }

   @Id
   public Integer getId() {
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