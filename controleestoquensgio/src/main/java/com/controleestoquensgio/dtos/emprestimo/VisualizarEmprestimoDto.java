package com.controleestoquensgio.dtos.emprestimo;

import com.controleestoquensgio.models.EmprestimoModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisualizarEmprestimoDto {

    private int id;
    private LocalDate dataDisponibilizacao;
    private LocalDate dataDevolucao;
    private int colaboradorId;
    private int equipamentoId;
    private String finalidade;
    private int respEntregaId;

    public VisualizarEmprestimoDto(EmprestimoModel emprestimoModel) {
        this.id = emprestimoModel.getId();
        this.dataDisponibilizacao = emprestimoModel.getDataDisponibilizacao();
        this.dataDevolucao = emprestimoModel.getDataDevolucao();
        this.colaboradorId = emprestimoModel.getColaborador().getId();
        this.equipamentoId = emprestimoModel.getEquipamento().getId();
        this.finalidade = emprestimoModel.getFinalidade();
        this.respEntregaId = emprestimoModel.getRespEntrega().getId();
    }
}