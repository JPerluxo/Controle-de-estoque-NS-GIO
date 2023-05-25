package com.controleestoquensgio.dtos.colaborador;

import com.controleestoquensgio.models.ColaboradorModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarColaboradoresDto {

    private int id;
    private String rf;
    private String nome;
    private String imagem;
    private String tipoAcesso;
    private String tipoColaborador;
    private String regimeTrabalho;
    private String ativo;

    public ListarColaboradoresDto(ColaboradorModel colaboradorModel) {
        this.id = colaboradorModel.getId();
        this.rf = colaboradorModel.getRf();
        this.nome = colaboradorModel.getNome();
        this.imagem = colaboradorModel.getImagem().getDescricao();
        this.tipoAcesso = colaboradorModel.getTipoAcesso().getDescricao();
        this.tipoColaborador = colaboradorModel.getTipoColaborador().getDescricao();
        this.regimeTrabalho = colaboradorModel.getRegimeTrabalho().getDescricao();
        this.ativo = colaboradorModel.getAtivo();
    }
}
