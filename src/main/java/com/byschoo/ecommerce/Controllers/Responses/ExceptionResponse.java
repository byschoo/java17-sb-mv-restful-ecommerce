package com.byschoo.ecommerce.Controllers.Responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Indica al serializador JSON que ignore los campos con valores null
public class ExceptionResponse {

    private String message;
    
    @JsonProperty("exception") // Especifica el nombre del campo en el JSON
    private Object object; // Mantén el nombre interno "object" si es necesario
    
    private String severity;
    private String code;
    private String url;
    private final LocalDateTime dateTime;
}
