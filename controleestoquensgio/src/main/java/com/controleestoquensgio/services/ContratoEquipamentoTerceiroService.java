package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.ContratoEquipamentoTerceiroRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContratoEquipamentoTerceiroService {
    final ContratoEquipamentoTerceiroRepository contratoEquipamentoTerceiroRpt;

    public ContratoEquipamentoTerceiroService (ContratoEquipamentoTerceiroRepository contratoEquipamentoTerceiroRpt){
        this.contratoEquipamentoTerceiroRpt = contratoEquipamentoTerceiroRpt;
    }

    @Transactional
    public Resultado save(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroMdl){

        contratoEquipamentoTerceiroRpt.save(contratoEquipamentoTerceiroMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    } 

    public Page<ContratoEquipamentoTerceiroModel> findAll(Pageable pageable) {
        return contratoEquipamentoTerceiroRpt.findAll(pageable);
    }

    public Optional<ContratoEquipamentoTerceiroModel> findById(Integer id) {
        return contratoEquipamentoTerceiroRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = findById(id);

        if (contratoEquipamentoTerceiroModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(), Mensagens.contratoDeEquipamentoDeTerceiroNaoEncontrado()
            );
        }

        contratoEquipamentoTerceiroRpt.delete(contratoEquipamentoTerceiroModelOptional.get());

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroMdl){

        contratoEquipamentoTerceiroRpt.save(contratoEquipamentoTerceiroMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    }
}
