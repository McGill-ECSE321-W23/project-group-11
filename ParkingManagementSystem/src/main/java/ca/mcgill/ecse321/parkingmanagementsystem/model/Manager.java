package ca.mcgill.ecse321.parkingmanagementsystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 30 "model.ump"
// line 120 "model.ump"
public class Manager extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Associations
  private ParkingManagementSystem parkingManagementSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(User aUser, ParkingManagementSystem aParkingManagementSystem)
  {
    super(aUser);
    boolean didAddParkingManagementSystem = setParkingManagementSystem(aParkingManagementSystem);
    if (!didAddParkingManagementSystem)
    {
      throw new RuntimeException("Unable to create manager due to parkingManagementSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ParkingManagementSystem getParkingManagementSystem()
  {
    return parkingManagementSystem;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setParkingManagementSystem(ParkingManagementSystem aNewParkingManagementSystem)
  {
    boolean wasSet = false;
    if (aNewParkingManagementSystem == null)
    {
      //Unable to setParkingManagementSystem to null, as manager must always be associated to a parkingManagementSystem
      return wasSet;
    }
    
    Manager existingManager = aNewParkingManagementSystem.getManager();
    if (existingManager != null && !equals(existingManager))
    {
      //Unable to setParkingManagementSystem, the current parkingManagementSystem already has a manager, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ParkingManagementSystem anOldParkingManagementSystem = parkingManagementSystem;
    parkingManagementSystem = aNewParkingManagementSystem;
    parkingManagementSystem.setManager(this);

    if (anOldParkingManagementSystem != null)
    {
      anOldParkingManagementSystem.setManager(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ParkingManagementSystem existingParkingManagementSystem = parkingManagementSystem;
    parkingManagementSystem = null;
    if (existingParkingManagementSystem != null)
    {
      existingParkingManagementSystem.setManager(null);
    }
    super.delete();
  }

}