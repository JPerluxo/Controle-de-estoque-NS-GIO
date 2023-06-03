/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos.imagem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarImagemDto {

    @NotBlank(message = "Insira uma descriçao!")
    private String  descricao;

}
