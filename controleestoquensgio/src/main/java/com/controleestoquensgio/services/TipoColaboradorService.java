package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.TipoColaboradorModel;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.repositories.TipoColaboradorRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TipoColaboradorService {
    final TipoColaboradorRepository tipoColaboradorRpt;

    public TipoColaboradorService (TipoColaboradorRepository tipoColaboradorRpt){
        this.tipoColaboradorRpt = tipoColaboradorRpt;
    }

    @Transactional
    public Resultado save(TipoColaboradorModel tipoColaboradorMdl) {

        tipoColaboradorRpt.save(tipoColaboradorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    }
    
    public Page<TipoColaboradorModel> findAll(Pageable pageable) {
        return tipoColaboradorRpt.findAll(pageable);
    }

    public Optional<TipoColaboradorModel> findById(Integer id) {
        return tipoColaboradorRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = findById(id);

        if (tipoColaboradorModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );
        }

        tipoColaboradorRpt.delete(tipoColaboradorModelOptional.get());

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(TipoColaboradorModel tipoColaboradorMdl){

        tipoColaboradorRpt.save(tipoColaboradorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
