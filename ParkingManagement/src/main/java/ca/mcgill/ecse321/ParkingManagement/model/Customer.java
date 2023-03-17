package ca.mcgill.ecse321.ParkingManagement.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer extends UserRole {
   
   @Id
   private int id;

   public void setId(int value) {
      this.id = value;
   }
   
   public int getId() {
      return this.id;
   }

}