package com.controleestoquensgio.dtos.emprestimo;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FiltrarEmprestimoDto {

    private Date dataDisponibilizacao;
    private Date dataDevolucao;
    private int colaboradorId;
    private int equipamentoId;
    private String ativo = SimOuNao.SIM.name();
    private String finalidade;
    private int respEntregaId;
    private ColaboradorModel colaborador;
    private EquipamentoModel equipamento;
    private ColaboradorModel respEntrega;

}