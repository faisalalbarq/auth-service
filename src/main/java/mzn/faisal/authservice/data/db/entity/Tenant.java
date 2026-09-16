package mzn.faisal.authservice.data.db.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "tenant")
public class Tenant {

    @Id
    @Column(name = "tenant_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tenantId;

    @Column(name = "party_id")
    private UUID partyId;

    @Column(name = "tenant_name")
    private String tenantName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

}
