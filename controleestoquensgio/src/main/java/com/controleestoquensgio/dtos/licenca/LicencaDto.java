/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos.licenca;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LicencaDto {

    @NotBlank(message = "Insira uma descrição!")
    private String descricao;

}