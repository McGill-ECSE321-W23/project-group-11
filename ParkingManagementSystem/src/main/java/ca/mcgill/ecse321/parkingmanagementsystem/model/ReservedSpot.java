package ca.mcgill.ecse321.parkingmanagementsystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 64 "model.ump"
// line 231 "model.ump"
public class ReservedSpot
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<int, ReservedSpot> reservedspotsById = new HashMap<int, ReservedSpot>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReservedSpot Attributes
  private int id;
  private float monthlyCost;

  //ReservedSpot Associations
  private ParkingManagementSystem parkingManagementSystem;
  private Car car;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReservedSpot(int aId, float aMonthlyCost, ParkingManagementSystem aParkingManagementSystem)
  {
    monthlyCost = aMonthlyCost;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddParkingManagementSystem = setParkingManagementSystem(aParkingManagementSystem);
    if (!didAddParkingManagementSystem)
    {
      throw new RuntimeException("Unable to create monthlySpot due to parkingManagementSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    int anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      reservedspotsById.remove(anOldId);
    }
    reservedspotsById.put(aId, this);
    return wasSet;
  }

  public boolean setMonthlyCost(float aMonthlyCost)
  {
    boolean wasSet = false;
    monthlyCost = aMonthlyCost;
    wasSet = true;
    return wasSet;
  }

  /**
   * spot number
   */
  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static ReservedSpot getWithId(int aId)
  {
    return reservedspotsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }

  public float getMonthlyCost()
  {
    return monthlyCost;
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
    //Must provide parkingManagementSystem to monthlySpot
    if (aParkingManagementSystem == null)
    {
      return wasSet;
    }

    //parkingManagementSystem already at maximum (200)
    if (aParkingManagementSystem.numberOfMonthlySpot() >= ParkingManagementSystem.maximumNumberOfMonthlySpot())
    {
      return wasSet;
    }
    
    ParkingManagementSystem existingParkingManagementSystem = parkingManagementSystem;
    parkingManagementSystem = aParkingManagementSystem;
    if (existingParkingManagementSystem != null && !existingParkingManagementSystem.equals(aParkingManagementSystem))
    {
      boolean didRemove = existingParkingManagementSystem.removeMonthlySpot(this);
      if (!didRemove)
      {
        parkingManagementSystem = existingParkingManagementSystem;
        return wasSet;
      }
    }
    parkingManagementSystem.addMonthlySpot(this);
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
    reservedspotsById.remove(getId());
    ParkingManagementSystem placeholderParkingManagementSystem = parkingManagementSystem;
    this.parkingManagementSystem = null;
    if(placeholderParkingManagementSystem != null)
    {
      placeholderParkingManagementSystem.removeMonthlySpot(this);
    }
    car = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "monthlyCost" + ":" + getMonthlyCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManagementSystem = "+(getParkingManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getParkingManagementSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null");
  }
}