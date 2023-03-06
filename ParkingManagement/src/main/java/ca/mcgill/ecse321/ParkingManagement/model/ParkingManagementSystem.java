package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 182 "model.ump"
public class ParkingManagementSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingManagementSystem Associations
  private Manager manager;
  private List<Employee> employees;
  private List<Customer> customers;
  private List<User> users;
  private List<Car> cars;
  private List<RegTempSpot> regSpots;
  private List<LargeTempSpot> largeSpots;
  private List<ServiceType> serviceTypes;
  private List<SpecificService> services;
  private List<SystemInfo> systemInfo;
  private List<ReservedSpot> monthlySpot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParkingManagementSystem()
  {
    employees = new ArrayList<Employee>();
    customers = new ArrayList<Customer>();
    users = new ArrayList<User>();
    cars = new ArrayList<Car>();
    regSpots = new ArrayList<RegTempSpot>();
    largeSpots = new ArrayList<LargeTempSpot>();
    serviceTypes = new ArrayList<ServiceType>();
    services = new ArrayList<SpecificService>();
    systemInfo = new ArrayList<SystemInfo>();
    monthlySpot = new ArrayList<ReservedSpot>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Manager getManager()
  {
    return manager;
  }

  public boolean hasManager()
  {
    boolean has = manager != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }
  /* Code from template association_GetMany */
  public Customer getCustomer(int index)
  {
    Customer aCustomer = customers.get(index);
    return aCustomer;
  }

  public List<Customer> getCustomers()
  {
    List<Customer> newCustomers = Collections.unmodifiableList(customers);
    return newCustomers;
  }

  public int numberOfCustomers()
  {
    int number = customers.size();
    return number;
  }

  public boolean hasCustomers()
  {
    boolean has = customers.size() > 0;
    return has;
  }

  public int indexOfCustomer(Customer aCustomer)
  {
    int index = customers.indexOf(aCustomer);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
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
  /* Code from template association_GetMany */
  public RegTempSpot getRegSpot(int index)
  {
    RegTempSpot aRegSpot = regSpots.get(index);
    return aRegSpot;
  }

  public List<RegTempSpot> getRegSpots()
  {
    List<RegTempSpot> newRegSpots = Collections.unmodifiableList(regSpots);
    return newRegSpots;
  }

  public int numberOfRegSpots()
  {
    int number = regSpots.size();
    return number;
  }

  public boolean hasRegSpots()
  {
    boolean has = regSpots.size() > 0;
    return has;
  }

  public int indexOfRegSpot(RegTempSpot aRegSpot)
  {
    int index = regSpots.indexOf(aRegSpot);
    return index;
  }
  /* Code from template association_GetMany */
  public LargeTempSpot getLargeSpot(int index)
  {
    LargeTempSpot aLargeSpot = largeSpots.get(index);
    return aLargeSpot;
  }

  public List<LargeTempSpot> getLargeSpots()
  {
    List<LargeTempSpot> newLargeSpots = Collections.unmodifiableList(largeSpots);
    return newLargeSpots;
  }

  public int numberOfLargeSpots()
  {
    int number = largeSpots.size();
    return number;
  }

  public boolean hasLargeSpots()
  {
    boolean has = largeSpots.size() > 0;
    return has;
  }

  public int indexOfLargeSpot(LargeTempSpot aLargeSpot)
  {
    int index = largeSpots.indexOf(aLargeSpot);
    return index;
  }
  /* Code from template association_GetMany */
  public ServiceType getServiceType(int index)
  {
    ServiceType aServiceType = serviceTypes.get(index);
    return aServiceType;
  }

  public List<ServiceType> getServiceTypes()
  {
    List<ServiceType> newServiceTypes = Collections.unmodifiableList(serviceTypes);
    return newServiceTypes;
  }

  public int numberOfServiceTypes()
  {
    int number = serviceTypes.size();
    return number;
  }

  public boolean hasServiceTypes()
  {
    boolean has = serviceTypes.size() > 0;
    return has;
  }

  public int indexOfServiceType(ServiceType aServiceType)
  {
    int index = serviceTypes.indexOf(aServiceType);
    return index;
  }
  /* Code from template association_GetMany */
  public SpecificService getService(int index)
  {
    SpecificService aService = services.get(index);
    return aService;
  }

  public List<SpecificService> getServices()
  {
    List<SpecificService> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(SpecificService aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_GetMany */
  public SystemInfo getSystemInfo(int index)
  {
    SystemInfo aSystemInfo = systemInfo.get(index);
    return aSystemInfo;
  }

  public List<SystemInfo> getSystemInfo()
  {
    List<SystemInfo> newSystemInfo = Collections.unmodifiableList(systemInfo);
    return newSystemInfo;
  }

  public int numberOfSystemInfo()
  {
    int number = systemInfo.size();
    return number;
  }

  public boolean hasSystemInfo()
  {
    boolean has = systemInfo.size() > 0;
    return has;
  }

  public int indexOfSystemInfo(SystemInfo aSystemInfo)
  {
    int index = systemInfo.indexOf(aSystemInfo);
    return index;
  }
  /* Code from template association_GetMany */
  public ReservedSpot getMonthlySpot(int index)
  {
    ReservedSpot aMonthlySpot = monthlySpot.get(index);
    return aMonthlySpot;
  }

  public List<ReservedSpot> getMonthlySpot()
  {
    List<ReservedSpot> newMonthlySpot = Collections.unmodifiableList(monthlySpot);
    return newMonthlySpot;
  }

  public int numberOfMonthlySpot()
  {
    int number = monthlySpot.size();
    return number;
  }

  public boolean hasMonthlySpot()
  {
    boolean has = monthlySpot.size() > 0;
    return has;
  }

  public int indexOfMonthlySpot(ReservedSpot aMonthlySpot)
  {
    int index = monthlySpot.indexOf(aMonthlySpot);
    return index;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setManager(Manager aNewManager)
  {
    boolean wasSet = false;
    if (manager != null && !manager.equals(aNewManager) && equals(manager.getParkingManagementSystem()))
    {
      //Unable to setManager, as existing manager would become an orphan
      return wasSet;
    }

    manager = aNewManager;
    ParkingManagementSystem anOldParkingManagementSystem = aNewManager != null ? aNewManager.getParkingManagementSystem() : null;

    if (!this.equals(anOldParkingManagementSystem))
    {
      if (anOldParkingManagementSystem != null)
      {
        anOldParkingManagementSystem.manager = null;
      }
      if (manager != null)
      {
        manager.setParkingManagementSystem(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployees()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Employee addEmployee(User aUser, String aSchedule)
  {
    return new Employee(aUser, aSchedule, this);
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    ParkingManagementSystem existingParkingManagementSystem = aEmployee.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);
    if (isNewParkingManagementSystem)
    {
      aEmployee.setParkingManagementSystem(this);
    }
    else
    {
      employees.add(aEmployee);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployee, as it must always have a parkingManagementSystem
    if (!this.equals(aEmployee.getParkingManagementSystem()))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Customer addCustomer(User aUser)
  {
    return new Customer(aUser, this);
  }

  public boolean addCustomer(Customer aCustomer)
  {
    boolean wasAdded = false;
    if (customers.contains(aCustomer)) { return false; }
    ParkingManagementSystem existingParkingManagementSystem = aCustomer.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);
    if (isNewParkingManagementSystem)
    {
      aCustomer.setParkingManagementSystem(this);
    }
    else
    {
      customers.add(aCustomer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomer(Customer aCustomer)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomer, as it must always have a parkingManagementSystem
    if (!this.equals(aCustomer.getParkingManagementSystem()))
    {
      customers.remove(aCustomer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAt(Customer aCustomer, int index)
  {  
    boolean wasAdded = false;
    if(addCustomer(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAt(Customer aCustomer, int index)
  {
    boolean wasAdded = false;
    if(customers.contains(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAt(aCustomer, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfUsersValid()
  {
    boolean isValid = numberOfUsers() >= minimumNumberOfUsers();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public User addUser(String aEmail, String aPassword)
  {
    User aNewUser = new User(aEmail, aPassword, this);
    return aNewUser;
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ParkingManagementSystem existingParkingManagementSystem = aUser.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);

    if (isNewParkingManagementSystem && existingParkingManagementSystem.numberOfUsers() <= minimumNumberOfUsers())
    {
      return wasAdded;
    }
    if (isNewParkingManagementSystem)
    {
      aUser.setParkingManagementSystem(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a parkingManagementSystem
    if (this.equals(aUser.getParkingManagementSystem()))
    {
      return wasRemoved;
    }

    //parkingManagementSystem already at minimum (1)
    if (numberOfUsers() <= minimumNumberOfUsers())
    {
      return wasRemoved;
    }

    users.remove(aUser);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCars()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Car addCar(String aLicensePlate, Car.Size aSize)
  {
    return new Car(aLicensePlate, aSize, this);
  }

  public boolean addCar(Car aCar)
  {
    boolean wasAdded = false;
    if (cars.contains(aCar)) { return false; }
    ParkingManagementSystem existingParkingManagementSystem = aCar.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);
    if (isNewParkingManagementSystem)
    {
      aCar.setParkingManagementSystem(this);
    }
    else
    {
      cars.add(aCar);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCar(Car aCar)
  {
    boolean wasRemoved = false;
    //Unable to remove aCar, as it must always have a parkingManagementSystem
    if (!this.equals(aCar.getParkingManagementSystem()))
    {
      cars.remove(aCar);
      wasRemoved = true;
    }
    return wasRemoved;
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfRegSpotsValid()
  {
    boolean isValid = numberOfRegSpots() >= minimumNumberOfRegSpots() && numberOfRegSpots() <= maximumNumberOfRegSpots();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfRegSpots()
  {
    return 250;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegSpots()
  {
    return 250;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRegSpots()
  {
    return 250;
  }
  /* Code from template association_AddMNToOnlyOne */
  public RegTempSpot addRegSpot(Date aStartTime, int aDuration, float aPricePer15)
  {
    if (numberOfRegSpots() >= maximumNumberOfRegSpots())
    {
      return null;
    }
    else
    {
      return new RegTempSpot(aStartTime, aDuration, aPricePer15, this);
    }
  }

  public boolean addRegSpot(RegTempSpot aRegSpot)
  {
    boolean wasAdded = false;
    if (regSpots.contains(aRegSpot)) { return false; }
    if (numberOfRegSpots() >= maximumNumberOfRegSpots())
    {
      return wasAdded;
    }

    ParkingManagementSystem existingParkingManagementSystem = aRegSpot.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);

    if (isNewParkingManagementSystem && existingParkingManagementSystem.numberOfRegSpots() <= minimumNumberOfRegSpots())
    {
      return wasAdded;
    }

    if (isNewParkingManagementSystem)
    {
      aRegSpot.setParkingManagementSystem(this);
    }
    else
    {
      regSpots.add(aRegSpot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegSpot(RegTempSpot aRegSpot)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegSpot, as it must always have a parkingManagementSystem
    if (this.equals(aRegSpot.getParkingManagementSystem()))
    {
      return wasRemoved;
    }

    //parkingManagementSystem already at minimum (250)
    if (numberOfRegSpots() <= minimumNumberOfRegSpots())
    {
      return wasRemoved;
    }
    regSpots.remove(aRegSpot);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfLargeSpotsValid()
  {
    boolean isValid = numberOfLargeSpots() >= minimumNumberOfLargeSpots() && numberOfLargeSpots() <= maximumNumberOfLargeSpots();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfLargeSpots()
  {
    return 20;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLargeSpots()
  {
    return 20;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfLargeSpots()
  {
    return 20;
  }
  /* Code from template association_AddMNToOnlyOne */
  public LargeTempSpot addLargeSpot(Date aStartTime, int aDuration, float aPricePer15)
  {
    if (numberOfLargeSpots() >= maximumNumberOfLargeSpots())
    {
      return null;
    }
    else
    {
      return new LargeTempSpot(aStartTime, aDuration, aPricePer15, this);
    }
  }

  public boolean addLargeSpot(LargeTempSpot aLargeSpot)
  {
    boolean wasAdded = false;
    if (largeSpots.contains(aLargeSpot)) { return false; }
    if (numberOfLargeSpots() >= maximumNumberOfLargeSpots())
    {
      return wasAdded;
    }

    ParkingManagementSystem existingParkingManagementSystem = aLargeSpot.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);

    if (isNewParkingManagementSystem && existingParkingManagementSystem.numberOfLargeSpots() <= minimumNumberOfLargeSpots())
    {
      return wasAdded;
    }

    if (isNewParkingManagementSystem)
    {
      aLargeSpot.setParkingManagementSystem(this);
    }
    else
    {
      largeSpots.add(aLargeSpot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLargeSpot(LargeTempSpot aLargeSpot)
  {
    boolean wasRemoved = false;
    //Unable to remove aLargeSpot, as it must always have a parkingManagementSystem
    if (this.equals(aLargeSpot.getParkingManagementSystem()))
    {
      return wasRemoved;
    }

    //parkingManagementSystem already at minimum (20)
    if (numberOfLargeSpots() <= minimumNumberOfLargeSpots())
    {
      return wasRemoved;
    }
    largeSpots.remove(aLargeSpot);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceType addServiceType(String aName, String aCost, String aDuration, Manager aManager)
  {
    return new ServiceType(aName, aCost, aDuration, this, aManager);
  }

  public boolean addServiceType(ServiceType aServiceType)
  {
    boolean wasAdded = false;
    if (serviceTypes.contains(aServiceType)) { return false; }
    ParkingManagementSystem existingParkingManagementSystem = aServiceType.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);
    if (isNewParkingManagementSystem)
    {
      aServiceType.setParkingManagementSystem(this);
    }
    else
    {
      serviceTypes.add(aServiceType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeServiceType(ServiceType aServiceType)
  {
    boolean wasRemoved = false;
    //Unable to remove aServiceType, as it must always have a parkingManagementSystem
    if (!this.equals(aServiceType.getParkingManagementSystem()))
    {
      serviceTypes.remove(aServiceType);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceTypeAt(ServiceType aServiceType, int index)
  {  
    boolean wasAdded = false;
    if(addServiceType(aServiceType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceTypes()) { index = numberOfServiceTypes() - 1; }
      serviceTypes.remove(aServiceType);
      serviceTypes.add(index, aServiceType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceTypeAt(ServiceType aServiceType, int index)
  {
    boolean wasAdded = false;
    if(serviceTypes.contains(aServiceType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceTypes()) { index = numberOfServiceTypes() - 1; }
      serviceTypes.remove(aServiceType);
      serviceTypes.add(index, aServiceType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceTypeAt(aServiceType, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificService addService(Date aDateAndTime, String aEmployee, ServiceType aServiceType, Car... allCars)
  {
    return new SpecificService(aDateAndTime, aEmployee, this, aServiceType, allCars);
  }

  public boolean addService(SpecificService aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    ParkingManagementSystem existingParkingManagementSystem = aService.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);
    if (isNewParkingManagementSystem)
    {
      aService.setParkingManagementSystem(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(SpecificService aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a parkingManagementSystem
    if (!this.equals(aService.getParkingManagementSystem()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(SpecificService aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(SpecificService aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSystemInfo()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SystemInfo addSystemInfo(String aOpenTime, String aCloseTime, Manager aManager)
  {
    return new SystemInfo(aOpenTime, aCloseTime, this, aManager);
  }

  public boolean addSystemInfo(SystemInfo aSystemInfo)
  {
    boolean wasAdded = false;
    if (systemInfo.contains(aSystemInfo)) { return false; }
    ParkingManagementSystem existingParkingManagementSystem = aSystemInfo.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);
    if (isNewParkingManagementSystem)
    {
      aSystemInfo.setParkingManagementSystem(this);
    }
    else
    {
      systemInfo.add(aSystemInfo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSystemInfo(SystemInfo aSystemInfo)
  {
    boolean wasRemoved = false;
    //Unable to remove aSystemInfo, as it must always have a parkingManagementSystem
    if (!this.equals(aSystemInfo.getParkingManagementSystem()))
    {
      systemInfo.remove(aSystemInfo);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSystemInfoAt(SystemInfo aSystemInfo, int index)
  {  
    boolean wasAdded = false;
    if(addSystemInfo(aSystemInfo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSystemInfo()) { index = numberOfSystemInfo() - 1; }
      systemInfo.remove(aSystemInfo);
      systemInfo.add(index, aSystemInfo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSystemInfoAt(SystemInfo aSystemInfo, int index)
  {
    boolean wasAdded = false;
    if(systemInfo.contains(aSystemInfo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSystemInfo()) { index = numberOfSystemInfo() - 1; }
      systemInfo.remove(aSystemInfo);
      systemInfo.add(index, aSystemInfo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSystemInfoAt(aSystemInfo, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfMonthlySpotValid()
  {
    boolean isValid = numberOfMonthlySpot() >= minimumNumberOfMonthlySpot() && numberOfMonthlySpot() <= maximumNumberOfMonthlySpot();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfMonthlySpot()
  {
    return 200;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMonthlySpot()
  {
    return 200;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfMonthlySpot()
  {
    return 200;
  }
  /* Code from template association_AddMNToOnlyOne */
  public ReservedSpot addMonthlySpot(int aId, float aMonthlyCost)
  {
    if (numberOfMonthlySpot() >= maximumNumberOfMonthlySpot())
    {
      return null;
    }
    else
    {
      return new ReservedSpot(aId, aMonthlyCost, this);
    }
  }

  public boolean addMonthlySpot(ReservedSpot aMonthlySpot)
  {
    boolean wasAdded = false;
    if (monthlySpot.contains(aMonthlySpot)) { return false; }
    if (numberOfMonthlySpot() >= maximumNumberOfMonthlySpot())
    {
      return wasAdded;
    }

    ParkingManagementSystem existingParkingManagementSystem = aMonthlySpot.getParkingManagementSystem();
    boolean isNewParkingManagementSystem = existingParkingManagementSystem != null && !this.equals(existingParkingManagementSystem);

    if (isNewParkingManagementSystem && existingParkingManagementSystem.numberOfMonthlySpot() <= minimumNumberOfMonthlySpot())
    {
      return wasAdded;
    }

    if (isNewParkingManagementSystem)
    {
      aMonthlySpot.setParkingManagementSystem(this);
    }
    else
    {
      monthlySpot.add(aMonthlySpot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMonthlySpot(ReservedSpot aMonthlySpot)
  {
    boolean wasRemoved = false;
    //Unable to remove aMonthlySpot, as it must always have a parkingManagementSystem
    if (this.equals(aMonthlySpot.getParkingManagementSystem()))
    {
      return wasRemoved;
    }

    //parkingManagementSystem already at minimum (200)
    if (numberOfMonthlySpot() <= minimumNumberOfMonthlySpot())
    {
      return wasRemoved;
    }
    monthlySpot.remove(aMonthlySpot);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    Manager existingManager = manager;
    manager = null;
    if (existingManager != null)
    {
      existingManager.delete();
      existingManager.setParkingManagementSystem(null);
    }
    while (employees.size() > 0)
    {
      Employee aEmployee = employees.get(employees.size() - 1);
      aEmployee.delete();
      employees.remove(aEmployee);
    }
    
    while (customers.size() > 0)
    {
      Customer aCustomer = customers.get(customers.size() - 1);
      aCustomer.delete();
      customers.remove(aCustomer);
    }
    
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (cars.size() > 0)
    {
      Car aCar = cars.get(cars.size() - 1);
      aCar.delete();
      cars.remove(aCar);
    }
    
    while (regSpots.size() > 0)
    {
      RegTempSpot aRegSpot = regSpots.get(regSpots.size() - 1);
      aRegSpot.delete();
      regSpots.remove(aRegSpot);
    }
    
    while (largeSpots.size() > 0)
    {
      LargeTempSpot aLargeSpot = largeSpots.get(largeSpots.size() - 1);
      aLargeSpot.delete();
      largeSpots.remove(aLargeSpot);
    }
    
    while (serviceTypes.size() > 0)
    {
      ServiceType aServiceType = serviceTypes.get(serviceTypes.size() - 1);
      aServiceType.delete();
      serviceTypes.remove(aServiceType);
    }
    
    while (services.size() > 0)
    {
      SpecificService aService = services.get(services.size() - 1);
      aService.delete();
      services.remove(aService);
    }
    
    while (systemInfo.size() > 0)
    {
      SystemInfo aSystemInfo = systemInfo.get(systemInfo.size() - 1);
      aSystemInfo.delete();
      systemInfo.remove(aSystemInfo);
    }
    
    while (monthlySpot.size() > 0)
    {
      ReservedSpot aMonthlySpot = monthlySpot.get(monthlySpot.size() - 1);
      aMonthlySpot.delete();
      monthlySpot.remove(aMonthlySpot);
    }
    
  }

}