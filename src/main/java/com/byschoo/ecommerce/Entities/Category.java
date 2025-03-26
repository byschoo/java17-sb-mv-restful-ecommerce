package com.byschoo.ecommerce.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "categories")
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name="category_name", nullable = false)
    private String categoryName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_disabled", nullable = false, columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean isDisabled = false;

    // Relación uno a muchos con la entidad Product
    // mappedBy: Indica el atributo de la entidad relacionada que mapea esta relación
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // Propagación de operaciones de persistencia y Eliminación de huérfanos
    @Builder.Default // Se inicializa como una lista vacía (en vez de nula), para evitar problemas de NullPointerException
    private List<Product> products = new ArrayList<>(); // Define una lista de productos asociados con la categoría

    // private List<Product> products;
    // private Category parentCategory;
    // private List<Category> childCategories;
    // private Date createdAt;
    // private Date updatedAt;
    // private Date deletedAt;
}
