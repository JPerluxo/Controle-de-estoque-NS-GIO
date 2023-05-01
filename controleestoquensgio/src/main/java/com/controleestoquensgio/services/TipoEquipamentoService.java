package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.TipoEquipamentoDto;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.TipoEquipamentoModel;
import com.controleestoquensgio.repositories.TipoEquipamentoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoEquipamentoService {
    final TipoEquipamentoRepository tipoEquipamentoRpt;

    public TipoEquipamentoService (TipoEquipamentoRepository tipoEquipamentoRpt){
        this.tipoEquipamentoRpt = tipoEquipamentoRpt;
    }

    @Transactional
    public Resultado save(TipoEquipamentoModel tipoEquipamentoMdl){

        tipoEquipamentoRpt.save(tipoEquipamentoMdl);

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    } 

    public Page<TipoEquipamentoModel> findAll(Pageable pageable) {
        return tipoEquipamentoRpt.findAll(pageable);
    }

    public Optional<TipoEquipamentoModel> findById(Integer id) {
        return tipoEquipamentoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) {
            return new Resultado(
                    Mensagens.tipoDeEquipamentoNaoEncontrado(),
                    Mensagens.tipoDeEquipamentoNaoEncontradoTipoDeMensagem()
            );
        }

        tipoEquipamentoRpt.delete(tipoEquipamentoModelOptional.get());

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    }

    @Transactional
    public Resultado update(TipoEquipamentoModel tipoEquipamentoMdl){

        tipoEquipamentoRpt.save(tipoEquipamentoMdl);

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    }

}
