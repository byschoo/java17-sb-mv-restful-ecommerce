package com.byschoo.ecommerce.Controllers.Responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Indica al serializador JSON que ignore los campos con valores null
public class SuccessResponse {

    private String mensaje;

    @JsonProperty("category") // Especifica el nombre del campo en el JSON
    private Object object; // Llama al campo "object" como "category" en el JSON

    private final LocalDateTime dateTime;
}
