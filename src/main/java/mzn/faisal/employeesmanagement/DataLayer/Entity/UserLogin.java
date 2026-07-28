package mzn.faisal.employeesmanagement.DataLayer.Entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_login")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_login_id")
    private UUID userLoginId;

    @Column(name = "party_id")
    private UUID partyId;

    @Column(name = "password")
    private String password;


    public UserLogin() {}

    public UserLogin(UUID userLoginId, UUID partyId, String password) {
        this.userLoginId = userLoginId;
        this.partyId = partyId;
        this.password = password;
    }

    public UUID getUserLoginId() {
        return userLoginId;
    }
    public void setUserLoginId(UUID userLoginId) {
        this.userLoginId = userLoginId;
    }

    public UUID getPartyId() {
        return partyId;
    }

    public void setPartyId(UUID partyId) {
        this.partyId = partyId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
