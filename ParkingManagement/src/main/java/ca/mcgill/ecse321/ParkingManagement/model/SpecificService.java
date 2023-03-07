package ca.mcgill.ecse321.ParkingManagement.model;
import jakarta.persistence.Entity;
import java.sql.Date;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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

private ParkingManagementSystem parkingManagementSystem;

@ManyToOne(optional=false)
public ParkingManagementSystem getParkingManagementSystem() {
   return this.parkingManagementSystem;
}

public void setParkingManagementSystem(ParkingManagementSystem parkingManagementSystem) {
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