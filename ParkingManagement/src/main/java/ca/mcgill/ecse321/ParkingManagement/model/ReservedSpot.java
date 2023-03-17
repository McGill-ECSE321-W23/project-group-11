package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class ReservedSpot {
   private Integer id;

   public void setId(Integer value) {
      this.id = value;
   }

   @Id
   public Integer getId() {
      return this.id;
   }

   private float monthlyCost;

   public void setMonthlyCost(float value) {
      this.monthlyCost = value;
   }

   public float getMonthlyCost() {
      return this.monthlyCost;
   }

   private Car car;

   @OneToOne(mappedBy = "reservedSpot")
   public Car getCar() {
      return this.car;
   }

   public void setCar(Car car) {
      this.car = car;
   }

   private ParkingManagementSystem parkingManagementSystem;

   @ManyToOne(optional = false)
   public ParkingManagementSystem getParkingManagementSystem() {
      return this.parkingManagementSystem;
   }

   public void setParkingManagementSystem(ParkingManagementSystem parkingManagementSystem) {
      this.parkingManagementSystem = parkingManagementSystem;
   }
}