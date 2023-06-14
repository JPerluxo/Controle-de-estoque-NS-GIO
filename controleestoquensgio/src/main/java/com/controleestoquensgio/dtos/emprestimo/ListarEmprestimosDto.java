package com.controleestoquensgio.dtos.emprestimo;

import com.controleestoquensgio.models.EmprestimoModel;
import com.controleestoquensgio.util.ConversorData;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ListarEmprestimosDto {

    private int id;
    private String dataDisponibilizacao;
    private String dataDevolucao;
    private String colaborador;
    private String equipamento;
    private String finalidade;
    private String respEntrega;
    private String ativo;

    public ListarEmprestimosDto (EmprestimoModel emprestimoModel) {
        this.id = emprestimoModel.getId();
        this.dataDisponibilizacao = ConversorData.converterData(emprestimoModel.getDataDisponibilizacao());
        this.dataDevolucao = getDataDeDevolucao(emprestimoModel.getDataDevolucao());
        this.colaborador = emprestimoModel.getColaborador().getNome();
        this.equipamento = emprestimoModel.getEquipamento().getNumPatrimonio();
        this.finalidade = emprestimoModel.getFinalidade();
        this.respEntrega = emprestimoModel.getRespEntrega().getNome();
        this.ativo = emprestimoModel.getAtivo();
    }

    public String getDataDeDevolucao (LocalDate data) {
        if (data != null) {
            return ConversorData.converterData(data);
        } else {
            return "";
        }
    }
}
