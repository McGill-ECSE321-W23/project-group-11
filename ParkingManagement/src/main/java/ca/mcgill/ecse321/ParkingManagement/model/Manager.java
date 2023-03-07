package ca.mcgill.ecse321.ParkingManagement.model;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Manager extends UserRole{
   private SystemInfo systemInfo;
   
   @OneToOne(optional=false)
   public SystemInfo getSystemInfo() {
      return this.systemInfo;
   }
   
   public void setSystemInfo(SystemInfo systemInfo) {
      this.systemInfo = systemInfo;
   }
   
   private Set<ServiceType> serviceType;
   
   @OneToMany(mappedBy="manager" )
   public Set<ServiceType> getServiceType() {
      return this.serviceType;
   }
   
   public void setServiceType(Set<ServiceType> serviceTypes) {
      this.serviceType = serviceTypes;
   }
   
   }