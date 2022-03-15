
/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

public class ProgramaDto {

    @NotBlank
    private String descricao;

    private String observacao;
    
    private LicencaDto licenca;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LicencaDto getLicenca() {
        return licenca;
    }
    public void setLicenca(LicencaDto licenca) {
        this.licenca = licenca;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
