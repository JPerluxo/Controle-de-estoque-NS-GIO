package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.TipoAcessoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoAcessoService {
    final TipoAcessoRepository tipoAcessoRpt;

    public TipoAcessoService (TipoAcessoRepository tipoAcessoRpt){
        this.tipoAcessoRpt = tipoAcessoRpt;
    }

    @Transactional
    public Resultado save(TipoAcessoModel tipoAcessoMdl){
        
        tipoAcessoRpt.save(tipoAcessoMdl);

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    } 

    public Page<TipoAcessoModel> findAll(Pageable pageable) {
        return tipoAcessoRpt.findAll(pageable);
    }

    public Optional<TipoAcessoModel> findById(Integer id) {
        return tipoAcessoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<TipoAcessoModel> tipoAcessoModelOptional = findById(id);

        if (tipoAcessoModelOptional.isEmpty()) {
            return new Resultado(
                    Mensagens.tipoDeAcessoNaoEncontrado(),
                    Mensagens.tipoDeAcessoNaoEncontradoTipoDeMensagem()
            );
        }

        tipoAcessoRpt.delete(tipoAcessoModelOptional.get());

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    }

    @Transactional
    public Resultado update(TipoAcessoModel tipoAcessoMdl){

        tipoAcessoRpt.save(tipoAcessoMdl);

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    }
}
