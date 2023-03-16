package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SpecificService {

   @Id
   private int id;

   public void setId(int value) {
      this.id = value;
   }
   
   public int getId() {
      return this.id;
   }
   
   private Date date;

   public void setDate(Date value) {
      this.date = value;
   }

   public Date getDate() {
      return this.date;
   }

   private Time startTime;

   public void setStartTime(Time value) {
      this.startTime = value;
   }

   public Time getStartTime() {
      return this.startTime;
   }


   private String employee;

   public void setEmployee(String value) {
      this.employee = value;
   }

   public String getEmployee() {
      return this.employee;
   }

   @ManyToOne(optional = false)
   private ServiceType serviceType;

   public ServiceType getServiceType() {
      return this.serviceType;
   }

   public void setServiceType(ServiceType serviceType) {
      this.serviceType = serviceType;
   }

   @ManyToOne(optional = false) 
   private Car car;

   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }
}