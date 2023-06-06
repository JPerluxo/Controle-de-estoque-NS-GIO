package com.controleestoquensgio.dtos.notaFiscal;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class NotaFiscalDto {

    @NotBlank(message = "Insira um número!")
    private String numero;

    @NotNull(message = "Insira uma data!")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

}
