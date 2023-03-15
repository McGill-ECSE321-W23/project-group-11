package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class SpecificService {
   
   @Id
   private Date dateAndTime;

   public void setDateAndTime(Date value) {
      this.dateAndTime = value;
   }

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

   @ManyToOne(optional = false)
   public ServiceType getServiceType() {
      return this.serviceType;
   }

   public void setServiceType(ServiceType serviceType) {
      this.serviceType = serviceType;
   }

   private Car car;

   @ManyToOne
   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }
}