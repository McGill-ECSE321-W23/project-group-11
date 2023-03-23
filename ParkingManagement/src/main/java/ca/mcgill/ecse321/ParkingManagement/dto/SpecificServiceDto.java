package ca.mcgill.ecse321.ParkingManagement.dto;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import java.sql.Date;
import java.time.LocalTime;



public class SpecificServiceDto{

    //Contructor
    public SpecificServiceDto(int id, Date date, LocalTime startTime, String employee, ServiceType serviceType, Car car) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.employee = employee;
        this.serviceType = serviceType;
        this.car = car;
    }
 
   private int id;
   private Date date;
   private LocalTime startTime;
   private String employee;
   private ServiceType serviceType;
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