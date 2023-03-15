package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.ManyToOne;

public abstract class UserRole {
   
   @ManyToOne(optional = false)
   private Account account;

   public Account getAccount() {
      return this.account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }
}