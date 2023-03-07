package ca.mcgill.ecse321.ParkingManagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public abstract class UserRole{
   private Account account;
   
   @ManyToOne(optional=false)
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
