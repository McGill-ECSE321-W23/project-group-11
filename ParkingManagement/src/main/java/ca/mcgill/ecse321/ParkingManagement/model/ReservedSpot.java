package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ReservedSpot {

   // attributes
   @Id
   @GeneratedValue
   private int id;

   private int placeNumber;
   private int month;
   private int year;

   // associations
   @ManyToOne
   private Car car;
   
   // getters
   public int getId() {
      return this.id;
   }
   public int getPlaceNumber() {
      return this.placeNumber;
   }
   public Car getCar() {
      return this.car;
   }
   public int getMonth() {
      return this.month;
   }
   public int getYear() {
      return this.year;
   }

   // setters
   public void setPlaceNumber(int value) {
      this.placeNumber = value;
   }
   public void setCar(Car car) {
      this.car = car;
   }
   public void setMonth(int month) {
      this.month = month;
   }   
   public void setYear(int year) {
      this.year = year;
   }
}