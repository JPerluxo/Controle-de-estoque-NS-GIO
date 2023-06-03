/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos.imagem;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarImagemDto {

    private String  descricao;
    private String ativo = SimOuNao.SIM.name();

}
