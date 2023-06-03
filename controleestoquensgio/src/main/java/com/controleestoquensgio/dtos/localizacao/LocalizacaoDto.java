package com.controleestoquensgio.dtos.localizacao;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizacaoDto{

    @NotBlank(message = "Insira um prédio!")
    private String predio;
    
    @NotBlank(message = "Insira um andar!")
    private String andar;
    
    @NotBlank(message = "Insira um lado!")
    private String lado;
    
    private String referencia;
       
}
