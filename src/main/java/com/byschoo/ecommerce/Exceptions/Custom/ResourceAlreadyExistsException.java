package com.byschoo.ecommerce.Exceptions.Custom;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) // Este parámetro indica que la generación de los métodos equals() y hashCode() debe incluir los campos de la superclase (si la hay).
public class ResourceAlreadyExistsException extends RuntimeException{

    private String code; // Código dinámico específico para cada una de las excepciones
    private HttpStatus status; // Estatus dinámico específico para cada una de las excepciones

    public ResourceAlreadyExistsException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public ResourceAlreadyExistsException(String message, String code, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.status = status;
    }
}
