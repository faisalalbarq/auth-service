package mzn.faisal.employeesmanagement.DataLayer.db.Entity;


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
@Table(name = "party")
public class Party {

    @Id
    @Column(name = "party_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID partyId;

    @Column(name = "party_name")
    private String partyName;
}
