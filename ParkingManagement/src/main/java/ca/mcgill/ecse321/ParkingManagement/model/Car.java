package ca.mcgill.ecse321.ParkingManagement.model;

import java.util.*;
import jakarta.persistence.*;


@Entity
public class Car
{

  public enum Size { Regular, Large }

  //Car Attributes
  private String licensePlate;
  private Size size;

  //Car Associations
  private ParkingManagementSystem parkingManagementSystem;


  public void setLicensePlate(String aLicensePlate)
  {
    this.licensePlate = aLicensePlate;
  }

  public void setSize(Size aSize)
  {
    this.size = aSize;
  }

  @Id
  public String getLicensePlate()
  {
    return this.licensePlate;
  }
  
  public Size getSize()
  {
    return this.size;
  }
  @ManyToOne
  public ParkingManagementSystem getParkingManagementSystem()
  {
    return parkingManagementSystem;
  }

  public void setParkingManagementSystem(ParkingManagement aParkingManagementSystem) {
    this.parkingManagementSystem = aParkingManagementSystem;
  }


}