package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "mandat")
public class Mandat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String reference;

    @Column(nullable = false) private LocalDate dateDebut;
    private LocalDate dateFin;

    // propriétaire de la relation 1–1
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "syndic_id", unique = true, nullable = false)
    @JsonIgnore
    private Syndic syndic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Syndic getSyndic() {
        return syndic;
    }

    public void setSyndic(Syndic syndic) {
        this.syndic = syndic;
    }
}
