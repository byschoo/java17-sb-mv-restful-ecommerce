package com.byschoo.ecommerce.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "is_disabled", nullable = false, columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean isDisabled = false;

    @JsonIgnore // Evita que el campo category sea serializado en la respuesta JSON. Si no se ignorara, se produciría un bucle infinito
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Define la relación muchos a uno con la entidad Category. FetchType.LAZY indica que la carga de la entidad relacionada se realiza de forma diferida (EAGER es automática, Esto puede ser costoso en términos de rendimiento). optional = false indica que la relación es obligatoria
    @JoinColumn(name = "category_id", nullable = false) // Product tendrá una columna category_id que actúa como clave foránea hacia la tabla Category. nullable = false indica que la relación es obligatoria
    private Category category; // Define la categoría a la que pertenece el producto

    // private Date createdAt;
    // private Date updatedAt;
    // private Date deletedAt;
    // private Category category;

}
