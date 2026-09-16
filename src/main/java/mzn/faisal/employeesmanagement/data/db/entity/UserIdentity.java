package mzn.faisal.employeesmanagement.data.db.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "user_identity")
public class UserIdentity {
    @Id
    @Column(name = "user_identity_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userIdentityId;

    @Column(name = "user_identity_type_id")
    private Integer userIdentityTypeId;

    @Column(name = "user_login_id")
    private UUID userLoginId;

    @Column(name = "user_identity_value")
    private String userIdentityValue;

}
