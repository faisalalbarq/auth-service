package mzn.faisal.employeesmanagement.DataLayer.Entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "party")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "party_id")
    private UUID partyId;

    public Party() {}

    public Party(UUID partyId) {
        this.partyId = partyId;
    }

    public UUID getPartyId() {
        return partyId;
    }
    public void setPartyId(UUID partyId) {
        this.partyId = partyId;
    }
}
