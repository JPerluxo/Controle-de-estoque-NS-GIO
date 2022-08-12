
/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

import com.controleestoquensgio.models.LicencaModel;

public class ProgramaDto {

    @NotBlank(message = "Insira uma descrição!")
    private String descricao;

    private String observacao;
    
    private LicencaModel licenca;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LicencaModel getLicenca() {
        return licenca;
    }
    public void setLicenca(LicencaModel licenca) {
        this.licenca = licenca;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
