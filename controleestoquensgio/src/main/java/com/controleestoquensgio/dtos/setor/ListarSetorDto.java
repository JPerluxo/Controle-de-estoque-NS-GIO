package com.controleestoquensgio.dtos.setor;

import com.controleestoquensgio.models.SetorModel;
import com.controleestoquensgio.util.NivelSetores;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarSetorDto {

    private int id;
    private String sigla;
    private String descricao;
    private String responsavel;
    private String nivel;
    private String ativo;

    public ListarSetorDto(SetorModel setorModel) {
        this.id = setorModel.getId();
        this.sigla = setorModel.getSigla();
        this.descricao = setorModel.getDescricao();
        this.responsavel = setorModel.getResponsavel().getNome();
        this.nivel = getNivelDescricao(setorModel.getNivel());
        this.ativo = setorModel.getAtivo();
    }

    public String getNivelDescricao (String nivel) {
        if (nivel.equals(NivelSetores.PRESIDENCIA.name())) {
            return "Presidencia";

        } else if (nivel.equals(NivelSetores.DIRETORIA.name())) {
            return "Diretoria";

        } else if (nivel.equals(NivelSetores.GERENCIA.name())) {
            return "Gerencia";

        } else {
            return "Núcleo";
        }
    }
}
