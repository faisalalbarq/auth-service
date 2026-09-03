package mzn.faisal.employeesmanagement.DataLayer.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "user_identity_type")
public class UserIdentityType {

    @Id
    @Column(name = "user_identity_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userIdentityTypeId;

    @Column(name = "user_identity_type_name")
    private String userIdentityTypeName;
}
