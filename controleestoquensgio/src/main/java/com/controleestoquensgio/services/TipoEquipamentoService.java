package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
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
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    } 

    public Page<TipoEquipamentoModel> findAll(Pageable pageable) {
        return tipoEquipamentoRpt.findAll(pageable);
    }
    public Page<TipoEquipamentoModel> findAllAtivo(Pageable pageable, String ativo) {
        return tipoEquipamentoRpt.findAllByAtivo(pageable, ativo);
    }

    public Optional<TipoEquipamentoModel> findById(Integer id) {
        return tipoEquipamentoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );
        }

        TipoEquipamentoModel tipoEquipamentoModel = tipoEquipamentoModelOptional.get();
        tipoEquipamentoModel.setAtivo(SimOuNao.NAO.name());

        tipoEquipamentoRpt.save(tipoEquipamentoModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(TipoEquipamentoModel tipoEquipamentoMdl){

        tipoEquipamentoRpt.save(tipoEquipamentoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
