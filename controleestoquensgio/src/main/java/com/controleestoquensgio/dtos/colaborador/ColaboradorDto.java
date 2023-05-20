package com.controleestoquensgio.dtos.colaborador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorDto{

    @NotBlank(message = "Insira o RF!")
    private String rf;

    @NotBlank(message = "Insira um nome!")
    private String nome;

    @Positive(message = "Insira uma imagem!")
    private int imagemId;

    @Positive(message = "Insira um tipo de acesso!")
    private int tipoAcessoId;

    @Positive(message = "Insira um tipo de colaborador!")
    private int tipoColaboradorId;

    @Positive(message = "Insira um regime de trabalho!")
    private int regimeTrabalhoId;

    private int presidenciaId;

    private int diretoriaId;

    private int gerenciaId;

    private int nucleoId;
}
