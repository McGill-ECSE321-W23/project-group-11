package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Manager extends UserRole {

   private int id;

   public void setId(int value) {
      this.id = value;
   }

   @Id
   public int getId() {
      return this.id;
   }
}