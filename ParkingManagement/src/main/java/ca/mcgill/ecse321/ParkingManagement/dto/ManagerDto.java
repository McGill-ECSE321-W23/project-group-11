package ca.mcgill.ecse321.ParkingManagement.dto;

public class ManagerDto {

    public int id;
    public String account;

    /**
     * Dto constructor
     * @param id admin id
     * @param account email identifier for account
     * 
     */
    
    public ManagerDto(int id, String account) {
        this.id = id;
        this.account = account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public int getId() {
        return this.id;
    }
}
