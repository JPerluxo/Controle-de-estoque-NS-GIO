package com.controleestoquensgio.dtos.tipoEquipamento;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoEquipamentoDto {
    
    @NotBlank(message = "Insira uma descrição!")
    private String descricao;
    
    @NotBlank(message = "Insira uma marca!")
    private String marca;

    @NotBlank(message = "Insira um modelo!")
    private String modelo;
    
    @NotBlank(message = "Insira um fornecedor!")
    private String fornecedor;
    
    private String polegadas;

}
