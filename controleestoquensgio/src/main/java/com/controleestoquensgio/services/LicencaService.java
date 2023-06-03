package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.licenca.FiltrarLicencaDto;
import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.repositories.LicencaQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.LicencaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LicencaService {
    final LicencaRepository licencaRpt;
    final LicencaQueryRepository licencaQueryRpt;

    public LicencaService (LicencaRepository licencaRpt, LicencaQueryRepository licencaQueryRpt){
        this.licencaRpt = licencaRpt;
        this.licencaQueryRpt = licencaQueryRpt;
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

    public Page<LicencaModel> findAllByFilter(Pageable pageable, FiltrarLicencaDto filtrarLicencaDto) {
        var licencas = licencaQueryRpt.customQuery(filtrarLicencaDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), licencas.size());

        return new PageImpl<>(licencas.subList(start, end), pageable, licencas.size());
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
