package com.controleestoquensgio.dtos.tipoEquipamento;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarTipoEquipamentoDto {

    private String descricao;
    private String marca;
    private String modelo;
    private String fornecedor;
    private String polegadas;
    private String ativo = SimOuNao.SIM.name();

}
