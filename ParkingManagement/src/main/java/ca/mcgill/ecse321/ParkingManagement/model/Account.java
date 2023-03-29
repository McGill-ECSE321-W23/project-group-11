package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
   // attributes
   @Id
   private String email;
   private String password;

   // getters
   public String getEmail() {
      return this.email;
   }
   public String getPassword() {
      return this.password;
   }

   // setters
   public void setEmail(String value) {
      this.email = value;
   }
   public void setPassword(String value) {
      this.password = value;
   }

   public Account(String email, String password) {
      this.email = email;
      this.password = password;
   }
   public Account() {
   }


}