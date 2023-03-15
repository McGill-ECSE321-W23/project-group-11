package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Account {

   @Id
   private String email;

   public void setEmail(String value) {
      this.email = value;
   }

   public String getEmail() {
      return this.email;
   }

   private String password;

   public void setPassword(String value) {
      this.password = value;
   }
   //probably should not have this
   public String getPassword() {
      return this.password;
   }

   @OneToMany
   private Set<Car> car;

   public Set<Car> getCar() {
      return this.car;
   }

   public void setCar(Set<Car> cars) {
      this.car = cars;
   }

}