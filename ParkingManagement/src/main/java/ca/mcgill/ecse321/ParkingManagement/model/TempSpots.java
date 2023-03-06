package ca.mcgill.ecse321.ParkingManagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 71 "model.ump"
// line 236 "model.ump"
// No tags, abstract
public abstract class TempSpots
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TempSpots Attributes
  private Date startTime;
  private int duration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TempSpots(Date aStartTime, int aDuration)
  {
    startTime = aStartTime;
    duration = aDuration;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Date aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  /**
   * assuming dates include time
   */
  public Date getStartTime()
  {
    return startTime;
  }

  /**
   * number of 15 minute intervals
   */
  public int getDuration()
  {
    return duration;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}