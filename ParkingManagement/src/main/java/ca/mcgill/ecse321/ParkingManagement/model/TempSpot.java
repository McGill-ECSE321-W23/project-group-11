package ca.mcgill.ecse321.ParkingManagement.model;



public abstract class TempSpot {
    // attributes
    private int duration; // number of 15 minute intervals
    

    // getters=
    public int getDuration() {
        return this.duration;
    }

    // setters
    public void setDuration(int value) {
        this.duration = value;
    }
}
