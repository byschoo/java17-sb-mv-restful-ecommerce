package com.byschoo.ecommerce.DTO;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable{
    @NotNull(message = "El identificador del producto es obligatorio")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s]+$", message = "El nombre del producto solo puede contener letras latinas y espacios")
    @Size(min = 2, max = 50, message = "El nombre del producto debe tener entre 2 y 50 caracteres")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 255, message = "La descripción del producto debe tener entre 10 y 255 caracteres")
    private String description;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String imageUrl;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio del producto debe ser mayor o igual a 0")
    private Double price;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock del producto debe ser mayor o igual a 0")
    private Integer stock;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoryId; // Identificador de la categoría asociada al producto
}
