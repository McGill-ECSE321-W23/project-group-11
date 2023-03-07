package ca.mcgill.ecse321.ParkingManagement.model;
import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import ca.mcgill.ecse321.parkingmanagement.model.ParkingManagement;

@Entity
public class SpecificService{
   private Date dateAndTime;

public void setDateAndTime(Date value) {
    this.dateAndTime = value;
}
@Id
public Date getDateAndTime() {
    return this.dateAndTime;
}
private String employee;

public void setEmployee(String value) {
    this.employee = value;
}
public String getEmployee() {
    return this.employee;
}
private ServiceType serviceType;

@ManyToOne(optional=false)
public ServiceType getServiceType() {
   return this.serviceType;
}

public void setServiceType(ServiceType serviceType) {
   this.serviceType = serviceType;
}

private Car car;

@OneToOne(mappedBy="specificService" , optional=false)
public Car getCar() {
   return this.car;
}

public void setCar(Car car) {
   this.car = car;
}

private ParkingManagement parkingManagementSystem;

@ManyToOne(optional=false)
public ParkingManagement getParkingManagementSystem() {
   return this.parkingManagementSystem;
}

public void setParkingManagementSystem(ParkingManagement parkingManagementSystem) {
   this.parkingManagementSystem = parkingManagementSystem;
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