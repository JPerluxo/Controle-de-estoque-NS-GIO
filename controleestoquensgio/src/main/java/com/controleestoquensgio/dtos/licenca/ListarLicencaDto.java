package com.controleestoquensgio.dtos.licenca;

import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarLicencaDto {

    private int id;
    private String descricao;

    public ListarLicencaDto(LicencaModel licencaModel) {
        this.id = licencaModel.getId();
        this.descricao = licencaModel.getDescricao();
    }
}