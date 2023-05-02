package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.util.ErroOuSucesso;
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
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
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
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeAcessoNaoEncontrado()
            );
        }

        tipoAcessoRpt.delete(tipoAcessoModelOptional.get());

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(TipoAcessoModel tipoAcessoMdl){

        tipoAcessoRpt.save(tipoAcessoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
