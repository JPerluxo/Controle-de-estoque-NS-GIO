package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.RegimeTrabalhoModel;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.RegimeTrabalhoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegimeTrabalhoService {
    final RegimeTrabalhoRepository regimeTrabalhoRpt;

    public RegimeTrabalhoService (RegimeTrabalhoRepository regimeTrabalhoRpt){
        this.regimeTrabalhoRpt = regimeTrabalhoRpt;
    }

    @Transactional
    public Resultado save(RegimeTrabalhoModel regimeTrabalhoMdl){

        regimeTrabalhoRpt.save(regimeTrabalhoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<RegimeTrabalhoModel> findAll(Pageable pageable) {
        return regimeTrabalhoRpt.findAll(pageable);
    }

    public Page<RegimeTrabalhoModel> findAllAtivo(Pageable pageable, String ativo) {
        return regimeTrabalhoRpt.findAllByAtivo(pageable, ativo);
    }

    public Optional<RegimeTrabalhoModel> findById(Integer id) {
        return regimeTrabalhoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = findById(id);

        if (regimeTrabalhoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.regimeDeTrabalhoNaoEncontrado()
            );
        }

        RegimeTrabalhoModel regimeTrabalhoModel = regimeTrabalhoModelOptional.get();
        regimeTrabalhoModel.setAtivo(SimOuNao.NAO.name());

        regimeTrabalhoRpt.save(regimeTrabalhoModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(RegimeTrabalhoModel regimeTrabalhoMdl){

        regimeTrabalhoRpt.save(regimeTrabalhoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

}
