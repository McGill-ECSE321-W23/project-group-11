package ca.mcgill.ecse321.ParkingManagement.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import ca.mcgill.ecse321.parkingmanagement.model.ParkingManagement;
import javax.persistence.ManyToOne;

@Entity
public class Account{
   private String email;

public void setEmail(String value) {
    this.email = value;
}
@Id
public String getEmail() {
    return this.email;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
   private Set<UserRole> userRole;
   
   @OneToMany(mappedBy="account" )
   public Set<UserRole> getUserRole() {
      return this.userRole;
   }
   
   public void setUserRole(Set<UserRole> userRoles) {
      this.userRole = userRoles;
   }
   
   private Set<Car> car;
   
   @OneToMany(mappedBy="account" )
   public Set<Car> getCar() {
      return this.car;
   }
   
   public void setCar(Set<Car> cars) {
      this.car = cars;
   }
   
   private ParkingManagement parkingManagementSystem;
   
   @ManyToOne(optional=false)
   public ParkingManagement getParkingManagementSystem() {
      return this.parkingManagementSystem;
   }
   
   public void setParkingManagementSystem(ParkingManagement parkingManagementSystem) {
      this.parkingManagementSystem = parkingManagementSystem;
   }
   
   }
