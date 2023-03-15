package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.ManyToOne;

public abstract class UserRole {
   private Account account;

   @ManyToOne(optional = false)
   public Account getAccount() {
      return this.account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }
}