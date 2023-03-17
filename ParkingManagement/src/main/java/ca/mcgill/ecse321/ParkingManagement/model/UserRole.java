package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public abstract class UserRole extends Account {
   private Account account;

   @ManyToOne(optional = false)
   public Account getAccount() {
      return this.account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   private int id;

   public void setId(int value) {
      this.id = value;
   }

   @Id
   public int getId() {
      return this.id;
   }
}
