package com.iteureka.backeurekafour.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="proyectos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProjectEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=255)
    private String nombre;

    @OneToMany(
        mappedBy = "proyecto_id",
        orphanRemoval = false,
        fetch = FetchType.EAGER
    )
    @ToString.Exclude
    @JsonManagedReference
    private List<CustomerEntity> clientes;
}
