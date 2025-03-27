package com.byschoo.ecommerce.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byschoo.ecommerce.Entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO implements Serializable{
    @NotNull(message = "El identificador de la categoría es obligatorio")
    private Long id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s]+$", message = "El nombre solo puede contener letras latinas y espacios")
    @Size(min = 2, max = 30, message = "El nombre de la categoría debe tener entre 2 y 30 caracteres")
    private String categoryName;

    @NotBlank(message = "La descripción de la categoría es obligatoria")
    @Size(min = 10, max = 255, message = "La descripción de la categoría debe tener entre 10 y 255 caracteres")
    private String description;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    @Size(min = 10, max = 255, message = "La URL de la imagen debe tener entre 10 y 255 caracteres")
    private String imageUrl;

    @JsonProperty("products")
    @Builder.Default
    private List<Product> products = new ArrayList<>();

}
