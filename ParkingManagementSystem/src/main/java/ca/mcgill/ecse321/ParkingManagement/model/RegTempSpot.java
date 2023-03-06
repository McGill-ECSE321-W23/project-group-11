package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 79 "model.ump"
// line 219 "model.ump"
public class RegTempSpot extends TempSpots
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RegTempSpot Attributes
  private float pricePer15;

  //RegTempSpot Associations
  private ParkingManagementSystem parkingManagementSystem;
  private Car car;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RegTempSpot(Date aStartTime, int aDuration, float aPricePer15, ParkingManagementSystem aParkingManagementSystem)
  {
    super(aStartTime, aDuration);
    pricePer15 = aPricePer15;
    boolean didAddParkingManagementSystem = setParkingManagementSystem(aParkingManagementSystem);
    if (!didAddParkingManagementSystem)
    {
      throw new RuntimeException("Unable to create regSpot due to parkingManagementSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPricePer15(float aPricePer15)
  {
    boolean wasSet = false;
    pricePer15 = aPricePer15;
    wasSet = true;
    return wasSet;
  }

  /**
   * price per 15 minute interval
   */
  public float getPricePer15()
  {
    return pricePer15;
  }
  /* Code from template association_GetOne */
  public ParkingManagementSystem getParkingManagementSystem()
  {
    return parkingManagementSystem;
  }
  /* Code from template association_GetOne */
  public Car getCar()
  {
    return car;
  }

  public boolean hasCar()
  {
    boolean has = car != null;
    return has;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setParkingManagementSystem(ParkingManagementSystem aParkingManagementSystem)
  {
    boolean wasSet = false;
    //Must provide parkingManagementSystem to regSpot
    if (aParkingManagementSystem == null)
    {
      return wasSet;
    }

    //parkingManagementSystem already at maximum (250)
    if (aParkingManagementSystem.numberOfRegSpots() >= ParkingManagementSystem.maximumNumberOfRegSpots())
    {
      return wasSet;
    }
    
    ParkingManagementSystem existingParkingManagementSystem = parkingManagementSystem;
    parkingManagementSystem = aParkingManagementSystem;
    if (existingParkingManagementSystem != null && !existingParkingManagementSystem.equals(aParkingManagementSystem))
    {
      boolean didRemove = existingParkingManagementSystem.removeRegSpot(this);
      if (!didRemove)
      {
        parkingManagementSystem = existingParkingManagementSystem;
        return wasSet;
      }
    }
    parkingManagementSystem.addRegSpot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setCar(Car aNewCar)
  {
    boolean wasSet = false;
    car = aNewCar;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ParkingManagementSystem placeholderParkingManagementSystem = parkingManagementSystem;
    this.parkingManagementSystem = null;
    if(placeholderParkingManagementSystem != null)
    {
      placeholderParkingManagementSystem.removeRegSpot(this);
    }
    car = null;
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "pricePer15" + ":" + getPricePer15()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManagementSystem = "+(getParkingManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getParkingManagementSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null");
  }
}