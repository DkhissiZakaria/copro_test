package com.app.copro.model;

import jakarta.persistence.*;
import java.util.ArrayList; import java.util.List;

@Entity @Table(name = "projet")
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nom;

    @Column(name = "id_make_plan")
    private Long idMakePlan;

    // Projet (1) -> (N) Syndic
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Syndic> syndics = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Syndic> getSyndics() {
        return syndics;
    }

    public void setSyndics(List<Syndic> syndics) {
        this.syndics = syndics;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getIdMakePlan() {
        return idMakePlan;
    }

    public void setIdMakePlan(Long idMakePlan) {
        this.idMakePlan = idMakePlan;
    }
}
