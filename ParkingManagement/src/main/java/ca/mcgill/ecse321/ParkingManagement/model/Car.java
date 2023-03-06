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
  /* Code from template association_SetOneToMany */
  public boolean setParkingManagementSystem(ParkingManagementSystem aParkingManagementSystem)
  {
    boolean wasSet = false;
    if (aParkingManagementSystem == null)
    {
      return wasSet;
    }

    ParkingManagementSystem existingParkingManagementSystem = parkingManagementSystem;
    parkingManagementSystem = aParkingManagementSystem;
    if (existingParkingManagementSystem != null && !existingParkingManagementSystem.equals(aParkingManagementSystem))
    {
      existingParkingManagementSystem.removeCar(this);
    }
    parkingManagementSystem.addCar(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    carsByLicensePlate.remove(getLicensePlate());
    ParkingManagementSystem placeholderParkingManagementSystem = parkingManagementSystem;
    this.parkingManagementSystem = null;
    if(placeholderParkingManagementSystem != null)
    {
      placeholderParkingManagementSystem.removeCar(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "licensePlate" + ":" + getLicensePlate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "size" + "=" + (getSize() != null ? !getSize().equals(this)  ? getSize().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManagementSystem = "+(getParkingManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getParkingManagementSystem())):"null");
  }
}