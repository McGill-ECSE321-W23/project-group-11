package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Manager extends UserRole {

   @Id
   private int id;

   public void setId(int value) {
      this.id = value;
   }

   public int getId() {
      return this.id;
   }

}