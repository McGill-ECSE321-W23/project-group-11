package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import ca.mcgill.ecse321.parkingmanagement.model.ParkingManagement;

@Entity
public class ServiceType{
   private String name;

public void setName(String value) {
    this.name = value;
}
@Id
public String getName() {
    return this.name;
}
private String cost;

public void setCost(String value) {
    this.cost = value;
}
public String getCost() {
    return this.cost;
}
private String duration;

public void setDuration(String value) {
    this.duration = value;
}
public String getDuration() {
    return this.duration;
}
   private Manager manager;
   
   @ManyToOne(optional=false)
   public Manager getManager() {
      return this.manager;
   }
   
   public void setManager(Manager manager) {
      this.manager = manager;
   }
   
   private Set<SpecificService> specificService;
   
   @OneToMany(mappedBy="serviceType" )
   public Set<SpecificService> getSpecificService() {
      return this.specificService;
   }
   
   public void setSpecificService(Set<SpecificService> specificServices) {
      this.specificService = specificServices;
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