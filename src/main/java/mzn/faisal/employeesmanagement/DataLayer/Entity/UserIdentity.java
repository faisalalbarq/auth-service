package mzn.faisal.employeesmanagement.DataLayer.Entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_identity")
public class UserIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_identity_id")
    private UUID userIdentityId;

    @Column(name = "user_login_id")
    private UUID userLoginId;

//    @Column(name = "party_id")
//    private UUID partyId;

    @Column(name = "user_identity_type_id")
    private Integer userIdentityTypeId;

    @Column(name = "user_identity_value")
    private String userIdentityValue;

    public UserIdentity() {}
    public UserIdentity(UUID userIdentityId, UUID userLoginId, Integer userIdentityTypeId, String userIdentityValue) {
        this.userIdentityId = userIdentityId;
        this.userLoginId = userLoginId;
        this.userIdentityTypeId = userIdentityTypeId;
        this.userIdentityValue = userIdentityValue;
    }

    public UUID getUserIdentityId() {
        return userIdentityId;
    }
    public void setUserIdentityId(UUID userIdentityId) {
        this.userIdentityId = userIdentityId;
    }

    public UUID getUserLoginId() {
        return userLoginId;
    }
    public void setUserLoginId(UUID userLoginId) {
        this.userLoginId = userLoginId;
    }

    public Integer getUserIdentityTypeId() {
        return userIdentityTypeId;
    }
    public void setUserIdentityTypeId(Integer userIdentityTypeId) {
        this.userIdentityTypeId = userIdentityTypeId;
    }

    public String getUserIdentityValue() {
        return userIdentityValue;
    }
    public void setUserIdentityValue(String userIdentityValue) {
        this.userIdentityValue = userIdentityValue;
    }
}
