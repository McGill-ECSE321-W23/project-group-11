package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;
import java.util.*;

// line 59 "model.ump"
// line 226 "model.ump"
public class SpecificService
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificService Attributes
  private Date dateAndTime;
  private String employee;

  //SpecificService Associations
  private ParkingManagementSystem parkingManagementSystem;
  private List<Car> cars;
  private ServiceType serviceType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificService(Date aDateAndTime, String aEmployee, ParkingManagementSystem aParkingManagementSystem, ServiceType aServiceType, Car... allCars)
  {
    dateAndTime = aDateAndTime;
    employee = aEmployee;
    boolean didAddParkingManagementSystem = setParkingManagementSystem(aParkingManagementSystem);
    if (!didAddParkingManagementSystem)
    {
      throw new RuntimeException("Unable to create service due to parkingManagementSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    cars = new ArrayList<Car>();
    boolean didAddCars = setCars(allCars);
    if (!didAddCars)
    {
      throw new RuntimeException("Unable to create SpecificService, must have at least 1 cars. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setServiceType(aServiceType))
    {
      throw new RuntimeException("Unable to create SpecificService due to aServiceType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDateAndTime(Date aDateAndTime)
  {
    boolean wasSet = false;
    dateAndTime = aDateAndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmployee(String aEmployee)
  {
    boolean wasSet = false;
    employee = aEmployee;
    wasSet = true;
    return wasSet;
  }

  public Date getDateAndTime()
  {
    return dateAndTime;
  }

  public String getEmployee()
  {
    return employee;
  }
  /* Code from template association_GetOne */
  public ParkingManagementSystem getParkingManagementSystem()
  {
    return parkingManagementSystem;
  }
  /* Code from template association_GetMany */
  public Car getCar(int index)
  {
    Car aCar = cars.get(index);
    return aCar;
  }

  public List<Car> getCars()
  {
    List<Car> newCars = Collections.unmodifiableList(cars);
    return newCars;
  }

  public int numberOfCars()
  {
    int number = cars.size();
    return number;
  }

  public boolean hasCars()
  {
    boolean has = cars.size() > 0;
    return has;
  }

  public int indexOfCar(Car aCar)
  {
    int index = cars.indexOf(aCar);
    return index;
  }
  /* Code from template association_GetOne */
  public ServiceType getServiceType()
  {
    return serviceType;
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
      existingParkingManagementSystem.removeService(this);
    }
    parkingManagementSystem.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCars()
  {
    return 1;
  }
  /* Code from template association_AddUnidirectionalMStar */
  public boolean addCar(Car aCar)
  {
    boolean wasAdded = false;
    if (cars.contains(aCar)) { return false; }
    cars.add(aCar);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCar(Car aCar)
  {
    boolean wasRemoved = false;
    if (!cars.contains(aCar))
    {
      return wasRemoved;
    }

    if (numberOfCars() <= minimumNumberOfCars())
    {
      return wasRemoved;
    }

    cars.remove(aCar);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMStar */
  public boolean setCars(Car... newCars)
  {
    boolean wasSet = false;
    ArrayList<Car> verifiedCars = new ArrayList<Car>();
    for (Car aCar : newCars)
    {
      if (verifiedCars.contains(aCar))
      {
        continue;
      }
      verifiedCars.add(aCar);
    }

    if (verifiedCars.size() != newCars.length || verifiedCars.size() < minimumNumberOfCars())
    {
      return wasSet;
    }

    cars.clear();
    cars.addAll(verifiedCars);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCarAt(Car aCar, int index)
  {  
    boolean wasAdded = false;
    if(addCar(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarAt(Car aCar, int index)
  {
    boolean wasAdded = false;
    if(cars.contains(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarAt(aCar, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setServiceType(ServiceType aNewServiceType)
  {
    boolean wasSet = false;
    if (aNewServiceType != null)
    {
      serviceType = aNewServiceType;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    ParkingManagementSystem placeholderParkingManagementSystem = parkingManagementSystem;
    this.parkingManagementSystem = null;
    if(placeholderParkingManagementSystem != null)
    {
      placeholderParkingManagementSystem.removeService(this);
    }
    cars.clear();
    serviceType = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "employee" + ":" + getEmployee()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dateAndTime" + "=" + (getDateAndTime() != null ? !getDateAndTime().equals(this)  ? getDateAndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManagementSystem = "+(getParkingManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getParkingManagementSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "serviceType = "+(getServiceType()!=null?Integer.toHexString(System.identityHashCode(getServiceType())):"null");
  }
}