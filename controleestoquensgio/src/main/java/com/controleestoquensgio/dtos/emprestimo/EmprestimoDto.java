package com.controleestoquensgio.dtos.emprestimo;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class EmprestimoDto {

    @NotNull(message = "Insira uma data de disponibilizacao!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDisponibilizacao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDevolucao;

    @Positive(message = "Insira um colaborador!")
    private int colaboradorId;

    @Positive(message = "Insira um equipamento!")
    private int equipamentoId;

    @NotBlank(message = "Insira a finalidade!")
    private String finalidade;

    @Positive(message = "Insira o responsável pela entrega!")
    private int respEntregaId;

}