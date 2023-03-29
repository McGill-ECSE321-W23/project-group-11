package ca.mcgill.ecse321.ParkingManagement.dto;

public class UserRoleDto {
    private String account;

    /**
     * @return account email
     */

    public String getAccount() {
        return this.account;
    }

    /**
     * @param account account email
     */

     public void setAccount(String account) {
        this.account = account;
     }
}
