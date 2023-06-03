package com.controleestoquensgio.dtos.emprestimo;

import com.controleestoquensgio.models.EmprestimoModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ListarEmprestimosDto {

    private int id;
    private Date dataDisponibilizacao;
    private Date dataDevolucao;
    private String colaborador;
    private String equipamento;
    private String finalidade;
    private String respEntrega;
    private String ativo;

    public ListarEmprestimosDto (EmprestimoModel emprestimoModel) {
        this.id = emprestimoModel.getId();
        this.dataDisponibilizacao = emprestimoModel.getDataDisponibilizacao();
        this.dataDevolucao = emprestimoModel.getDataDevolucao();
        this.colaborador = emprestimoModel.getColaborador().getNome();
        this.equipamento = emprestimoModel.getEquipamento().getNumPatrimonio();
        this.finalidade = emprestimoModel.getFinalidade();
        this.respEntrega = emprestimoModel.getRespEntrega().getNome();
        this.ativo = emprestimoModel.getAtivo();
    }
}
