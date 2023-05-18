package com.controleestoquensgio.dtos.licenca;

import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarLicencaDto {

    private int id;
    private String descricao;

    public VisualizarLicencaDto(LicencaModel licencaModel) {
        this.id = licencaModel.getId();
        this.descricao = licencaModel.getDescricao();
    }
}