package ca.mcgill.ecse321.ParkingManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Car {
   private String licensePlate;

   public void setLicensePlate(String value) {
      this.licensePlate = value;
   }

   @Id
   public String getLicensePlate() {
      return this.licensePlate;
   }

   private Size size;

   public void setSize(Size value) {
      this.size = value;
   }

   public Size getSize() {
      return this.size;
   }

   private Account account;

   @ManyToOne(optional = false)
   public Account getAccount() {
      return this.account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   private ReservedSpot reservedSpot;

   @OneToOne
   public ReservedSpot getReservedSpot() {
      return this.reservedSpot;
   }

   public void setReservedSpot(ReservedSpot reservedSpot) {
      this.reservedSpot = reservedSpot;
   }

   private SpecificService specificService;

   @OneToOne
   public SpecificService getSpecificService() {
      return this.specificService;
   }

   public void setSpecificService(SpecificService specificService) {
      this.specificService = specificService;
   }

   private RegularTempSpot regularTempSpot;

   @OneToOne
   public RegularTempSpot getRegularTempSpot() {
      return this.regularTempSpot;
   }

   public void setRegularTempSpot(RegularTempSpot regularTempSpot) {
      this.regularTempSpot = regularTempSpot;
   }

   private LargeTempSpot largeTempSpot;

   @OneToOne
   public LargeTempSpot getLargeTempSpot() {
      return this.largeTempSpot;
   }

   public void setLargeTempSpot(LargeTempSpot largeTempSpot) {
      this.largeTempSpot = largeTempSpot;
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
