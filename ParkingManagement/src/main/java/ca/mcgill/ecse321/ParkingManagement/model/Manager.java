package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Manager extends UserRole {

   @Id
   @GeneratedValue
   private int id;

   public int getId() {
      return this.id;
   }

}