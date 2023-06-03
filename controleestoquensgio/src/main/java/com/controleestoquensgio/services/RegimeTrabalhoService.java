package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.regimeTrabalho.FiltrarRegimeTrabalhoDto;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import com.controleestoquensgio.repositories.RegimeTrabalhoQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.RegimeTrabalhoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegimeTrabalhoService {
    final RegimeTrabalhoRepository regimeTrabalhoRpt;

    final RegimeTrabalhoQueryRepository regimeTrabalhoQueryRpt;

    public RegimeTrabalhoService (RegimeTrabalhoRepository regimeTrabalhoRpt, RegimeTrabalhoQueryRepository regimeTrabalhoQueryRpt){
        this.regimeTrabalhoRpt = regimeTrabalhoRpt;
        this.regimeTrabalhoQueryRpt = regimeTrabalhoQueryRpt;
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

    public Page<RegimeTrabalhoModel> findAllByFilter(Pageable pageable, FiltrarRegimeTrabalhoDto filtrarRegimeTrabalhoDto) {
        var regimesTrabalho = regimeTrabalhoQueryRpt.customQuery(filtrarRegimeTrabalhoDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), regimesTrabalho.size());

        return new PageImpl<>(regimesTrabalho.subList(start, end), pageable, regimesTrabalho.size());
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
