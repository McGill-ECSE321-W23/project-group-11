package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class UserRole {

   @OneToOne(optional = false)
   private Account account;

   public Account getAccount() {
      return this.account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

}