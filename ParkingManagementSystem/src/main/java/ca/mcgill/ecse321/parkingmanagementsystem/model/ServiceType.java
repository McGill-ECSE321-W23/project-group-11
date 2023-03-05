package ca.mcgill.ecse321.parkingmanagementsystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 52 "model.ump"
// line 200 "model.ump"
public class ServiceType
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, ServiceType> servicetypesByName = new HashMap<String, ServiceType>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceType Attributes
  private String name;
  private String cost;
  private String duration;

  //ServiceType Associations
  private ParkingManagementSystem parkingManagementSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceType(String aName, String aCost, String aDuration, ParkingManagementSystem aParkingManagementSystem)
  {
    cost = aCost;
    duration = aDuration;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddParkingManagementSystem = setParkingManagementSystem(aParkingManagementSystem);
    if (!didAddParkingManagementSystem)
    {
      throw new RuntimeException("Unable to create serviceType due to parkingManagementSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      servicetypesByName.remove(anOldName);
    }
    servicetypesByName.put(aName, this);
    return wasSet;
  }

  public boolean setCost(String aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(String aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static ServiceType getWithName(String aName)
  {
    return servicetypesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public String getCost()
  {
    return cost;
  }

  public String getDuration()
  {
    return duration;
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
      existingParkingManagementSystem.removeServiceType(this);
    }
    parkingManagementSystem.addServiceType(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    servicetypesByName.remove(getName());
    ParkingManagementSystem placeholderParkingManagementSystem = parkingManagementSystem;
    this.parkingManagementSystem = null;
    if(placeholderParkingManagementSystem != null)
    {
      placeholderParkingManagementSystem.removeServiceType(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "cost" + ":" + getCost()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManagementSystem = "+(getParkingManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getParkingManagementSystem())):"null");
  }
}