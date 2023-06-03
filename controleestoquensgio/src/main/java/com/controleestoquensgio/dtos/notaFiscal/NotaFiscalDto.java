package com.controleestoquensgio.dtos.notaFiscal;

import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

}
