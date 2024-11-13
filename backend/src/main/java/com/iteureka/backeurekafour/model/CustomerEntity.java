package com.iteureka.backeurekafour.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="clientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerEntity  {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    @ToString.Exclude
    @JsonBackReference
    private ProjectEntity proyecto_id;

    @Column(nullable = false, length=255)
    private String nombre;
    
    @Column(nullable = false, length=255)
    private String casa_matriz;
}
