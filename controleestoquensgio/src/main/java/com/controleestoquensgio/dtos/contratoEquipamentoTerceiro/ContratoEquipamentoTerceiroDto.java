package com.controleestoquensgio.dtos.contratoEquipamentoTerceiro;

import java.time.LocalDate;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class ContratoEquipamentoTerceiroDto {
    
    @NotBlank(message = "Insira um fornecedor!")
    private String fornecedor;

    @NotNull(message = "Insira uma data de início!")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @NotNull(message = "Insira uma data final!")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFinal;
}