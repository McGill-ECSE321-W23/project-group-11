package ca.mcgill.ecse321.parkingmanagementsystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 26 "model.ump"
// line 94 "model.ump"
public class Employee extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private String schedule;

  //Employee Associations
  private ParkingManagementSystem parkingManagementSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(User aUser, String aSchedule, ParkingManagementSystem aParkingManagementSystem)
  {
    super(aUser);
    schedule = aSchedule;
    boolean didAddParkingManagementSystem = setParkingManagementSystem(aParkingManagementSystem);
    if (!didAddParkingManagementSystem)
    {
      throw new RuntimeException("Unable to create employee due to parkingManagementSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSchedule(String aSchedule)
  {
    boolean wasSet = false;
    schedule = aSchedule;
    wasSet = true;
    return wasSet;
  }

  public String getSchedule()
  {
    return schedule;
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
      existingParkingManagementSystem.removeEmployee(this);
    }
    parkingManagementSystem.addEmployee(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ParkingManagementSystem placeholderParkingManagementSystem = parkingManagementSystem;
    this.parkingManagementSystem = null;
    if(placeholderParkingManagementSystem != null)
    {
      placeholderParkingManagementSystem.removeEmployee(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "schedule" + ":" + getSchedule()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManagementSystem = "+(getParkingManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getParkingManagementSystem())):"null");
  }
}