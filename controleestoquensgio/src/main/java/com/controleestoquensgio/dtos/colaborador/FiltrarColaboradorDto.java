package com.controleestoquensgio.dtos.colaborador;

import com.controleestoquensgio.models.*;
import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarColaboradorDto {

    private String rf;
    private String nome;
    private int imagemId;
    private int tipoAcessoId;
    private int tipoColaboradorId;
    private int regimeTrabalhoId;
    private int presidenciaId;
    private int diretoriaId;
    private int gerenciaId;
    private int nucleoId;
    private String ativo = SimOuNao.SIM.name();

    private ImagemModel imagem;
    private TipoAcessoModel tipoAcesso;
    private TipoColaboradorModel tipoColaborador;
    private RegimeTrabalhoModel regimeTrabalho;
    private SetorModel presidencia;
    private SetorModel diretoria;
    private SetorModel gerencia;
    private SetorModel nucleo;
}
