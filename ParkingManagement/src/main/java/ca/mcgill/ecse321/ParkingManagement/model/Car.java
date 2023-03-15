package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Entity
public class Car {

   private String licensePlate;

   public void setLicensePlate(String value) {
      this.licensePlate = value;
   }

   @Id
   public String getLicensePlate() {
      return this.licensePlate;
   }

   @Enumerated(EnumType.ORDINAL) 
   private Size size;

   public void setSize(Size value) {
      this.size = value;
   }

   public Size getSize() {
      return this.size;
   }

  
}