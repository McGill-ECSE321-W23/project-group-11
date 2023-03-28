package ca.mcgill.ecse321.ParkingManagement.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer extends UserRole {
   // attributes
   @Id
   @GeneratedValue
   private int id;
   
   // getters
   public int getId() {
      return this.id;
   }
}