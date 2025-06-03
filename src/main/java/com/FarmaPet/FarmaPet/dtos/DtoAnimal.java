package com.FarmaPet.FarmaPet.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DtoAnimal(

        Integer id,    
        @NotBlank String nome,
        @NotBlank String especie,
        @NotBlank String raca,
        @Min(0)     Integer idade,
        @Positive   Float   peso

) {}