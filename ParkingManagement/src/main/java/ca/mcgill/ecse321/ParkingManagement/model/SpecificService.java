package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;


@Entity
@IdClass(SpecificServiceId.class)
public class SpecificService {

   @Id 
   @ManyToOne(optional = false)
   private ServiceType serviceType;

   @Id 
   @ManyToOne(optional = false)
   private Car car;

   private Date dateAndTime;

   private String employee;
    
   public void setDateAndTime(Date value) {
      this.dateAndTime = value;
   }

   public Date getDate() {
      return this.date;
   }

   public void setEmployee(String value) {
      this.employee = value;
   }

   public String getEmployee() {
      return this.employee;
   }

   public ServiceType getServiceType() {
      return this.serviceType;
   }

   public void setServiceType(ServiceType serviceType) {
      this.serviceType = serviceType;
   }

   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }

}