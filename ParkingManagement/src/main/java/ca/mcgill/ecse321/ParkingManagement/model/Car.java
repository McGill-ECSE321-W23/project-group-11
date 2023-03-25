package ca.mcgill.ecse321.ParkingManagement.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Car {

   @Id
   private String licensePlate;

   @ManyToMany
   private Set<Employee> employees;

   @ManyToOne(optional=true)
   private Customer customer;

   @ManyToOne
   private Manager manager;
   //default contructor
   public Car(){

   }
   //contructor
   public Car(String licensePlate,Set<Employee> employees, Customer customer, Manager manager){
      this.licensePlate = licensePlate;
      this.employees = employees;
      this.customer = customer;
      this.manager = manager;
   }

   public void setLicensePlate(String value) {
      this.licensePlate = value;
   }

   public String getLicensePlate() {
      return this.licensePlate;
   }

   @Enumerated(EnumType.ORDINAL) 
   private Size size;

   public void setSize(Size value) {
      this.size = value;
   }

   public Size getSize() {
      return this.size;
   }

   public Customer getCustomer() {
      return this.customer;
   }

   public void setCustomer(Customer customer) {
      this.customer= customer;
   }

   public Set<Employee> getEmployees() {
      return this.employees;
   }

   public void setEmployees(Set<Employee> employees) {
      this.employees = employees;
   }

   public Manager getManager() {
      return this.manager;
   }

   public void setManager(Manager manager) {
      this.manager= manager;
   }


}