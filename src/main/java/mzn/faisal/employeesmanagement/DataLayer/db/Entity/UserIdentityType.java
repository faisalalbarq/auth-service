package mzn.faisal.employeesmanagement.DataLayer.db.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mzn.faisal.employeesmanagement.DataLayer.db.base.LangAttribute;
import mzn.faisal.employeesmanagement.DataLayer.db.base.LangAttributeConverter;

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
    private Short userIdentityTypeId;

    @Column(name = "user_identity_type_name")
    private LangAttribute userIdentityTypeName;
}
