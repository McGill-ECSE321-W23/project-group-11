package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// line 40 "model.ump"
// line 154 "model.ump"
@Entity
public class Car
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Size { Regular, Large }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Car> carsByLicensePlate = new HashMap<String, Car>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private String licensePlate;
  private Size size;

  //Car Associations
  private ParkingManagementSystem parkingManagementSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Car(String aLicensePlate, Size aSize, ParkingManagementSystem aParkingManagementSystem)
  {
    size = aSize;
    if (!setLicensePlate(aLicensePlate))
    {
      throw new RuntimeException("Cannot create due to duplicate licensePlate. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddParkingManagementSystem = setParkingManagementSystem(aParkingManagementSystem);
    if (!didAddParkingManagementSystem)
    {
      throw new RuntimeException("Unable to create car due to parkingManagementSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLicensePlate(String aLicensePlate)
  {
    boolean wasSet = false;
    String anOldLicensePlate = getLicensePlate();
    if (anOldLicensePlate != null && anOldLicensePlate.equals(aLicensePlate)) {
      return true;
    }
    if (hasWithLicensePlate(aLicensePlate)) {
      return wasSet;
    }
    licensePlate = aLicensePlate;
    wasSet = true;
    if (anOldLicensePlate != null) {
      carsByLicensePlate.remove(anOldLicensePlate);
    }
    carsByLicensePlate.put(aLicensePlate, this);
    return wasSet;
  }

  public boolean setSize(Size aSize)
  {
    boolean wasSet = false;
    size = aSize;
    wasSet = true;
    return wasSet;
  }

  @Id
  public String getLicensePlate()
  {
    return licensePlate;
  }
  /* Code from template attribute_GetUnique */
  public static Car getWithLicensePlate(String aLicensePlate)
  {
    return carsByLicensePlate.get(aLicensePlate);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithLicensePlate(String aLicensePlate)
  {
    return getWithLicensePlate(aLicensePlate) != null;
  }

  @ManyToOne
  public Size getSize()
  {
    return size;
  }
  /* Code from template association_GetOne */
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