package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.LicencaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LicencaService {
    final LicencaRepository licencaRpt;

    public LicencaService (LicencaRepository licencaRpt){
        this.licencaRpt = licencaRpt;
    }

    @Transactional
    public Resultado save(LicencaModel licencaMdl){

        licencaRpt.save(licencaMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<LicencaModel> findAll(Pageable pageable) {
        return licencaRpt.findAll(pageable);
    }

    public Page<LicencaModel> findAllAtivo(Pageable pageable, String ativo) {
        return licencaRpt.findAllByAtivo(pageable, ativo);
    }

    public Optional<LicencaModel> findById(Integer id) {
        return licencaRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<LicencaModel> licencaModelOptional = findById(id);

        if (licencaModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.licencaNaoEncontrada()
            );
        }

        LicencaModel licencaModel = licencaModelOptional.get();
        licencaModel.setAtivo(SimOuNao.NAO.name());

        licencaRpt.save(licencaModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(LicencaModel licencaMdl){

        licencaRpt.save(licencaMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
