package ca.mcgill.ecse321.ParkingManagement.model;

import java.sql.Date;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RegularTempSpot extends TempSpot {
   // attributes
   @Id
   @GeneratedValue
   private int id;
   private int placeNumber;

   // associations
   @ManyToOne
   private Car car;

   // getters
   public int getPlaceNumber() {
      return this.placeNumber;
   }
   public int getId() {
      return this.id;
   }
   public Car getCar() {
      return this.car;
   }
   
   // setters
   public void setPlaceNumber(int value) {
      this.placeNumber = value;
   }
   public void setCar(Car car) {
      this.car = car;
   }


   /*
    * previously in tempspot
    */
   private Date date;
   private LocalTime startTime;
   

   // getters
   public Date getDate() {
      return this.date;
   }
   public LocalTime getStartTime() {
      return this.startTime;
   }

   // setters
   public void setDate(Date value) {
      this.date = value;
   }
   public void setStartTime(LocalTime value) {
      this.startTime = value;
   }


}