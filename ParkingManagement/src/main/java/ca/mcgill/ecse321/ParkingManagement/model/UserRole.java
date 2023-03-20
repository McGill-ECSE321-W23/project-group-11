package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class UserRole {
   // associations
   @OneToOne(optional = false)
   private Account account;

   // getters
   public Account getAccount() {
      return this.account;
   }

   // setters
   public void setAccount(Account account) {
      this.account = account;
   }
}