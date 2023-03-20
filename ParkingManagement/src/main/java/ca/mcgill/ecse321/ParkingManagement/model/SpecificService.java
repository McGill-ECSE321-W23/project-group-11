package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"car_licensePlate", "serviceType_name"}))
public class SpecificService {

   @Id
   @GeneratedValue
   private int id;

   private Date date;
   private LocalTime startTime;
   private String employee;

   @ManyToOne
   @JoinColumn(name = "serviceType_name")
   private ServiceType serviceType;

   @ManyToOne
   @JoinColumn(name = "car_licensePlate")
   private Car car;


   public int getId() {
      return this.id;
   }
  
   public void setDate(Date value) {
      this.date = value;
   }
   
   public Date getDate() {
      return this.date;
   }
   
   public void setStartTime(LocalTime value) {
      this.startTime = value;
   }
   
   public LocalTime getStartTime() {
      return this.startTime;
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