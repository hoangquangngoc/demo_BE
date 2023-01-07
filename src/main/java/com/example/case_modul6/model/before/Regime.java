package com.example.case_modul6.model.before;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Regime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idRegime;

    private String nameRegime;
}
