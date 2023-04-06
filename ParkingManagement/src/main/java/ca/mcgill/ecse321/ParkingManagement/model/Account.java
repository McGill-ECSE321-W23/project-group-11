package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Account {
   // attributes
   @Id
   private String email;
   private String password;
   private boolean loginStatus; 

   // getters
   public String getEmail() {
      return this.email;
   }

   public String getPassword() {
      return this.password;
   }

   public boolean getLoginStatus() {
      return this.loginStatus;
   }

   // setters
   public void setEmail(String value) {
      this.email = value;
   }

   public void setPassword(String value) {
      this.password = value;
   }

   public void setloginStatus(boolean loginStatus) {
      this.loginStatus = loginStatus;
   }

   public Account(String email, String password, boolean loginStatus) {
      this.email = email;
      this.password = password;
      this.loginStatus = loginStatus;
   }
   
   public Account() {
   }


}