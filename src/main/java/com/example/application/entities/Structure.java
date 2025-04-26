package com.example.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "structures")
public class Structure {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 1000000000, allocationSize = 1)
  private Long id;

  @Column
  private String nom;

  @Column
  private String telephone;
  
  @Column
  private String eamail;
  
  @Column
  private String localisation;

  public Structure(){

  }
  public Structure(String nom){
    this.nom = nom;
  }

    
}
