package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;

import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SpecificService {

   @Id
   private int id;
   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   @ManyToOne
   private ServiceType serviceType;

   @ManyToOne
   private Car car;

   private Date dateAndTime;

   private String employee;
   
   public void setDateAndTime(Date value) {
      this.dateAndTime = value;
   }

   public Date getDate() {
      return this.dateAndTime;
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