package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.programa.ProgramaDto;
import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.models.ProgramaModel;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.ProgramaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProgramaService {

    @Autowired
    LicencaService licencaService;

    final ProgramaRepository programaRpt;

    public ProgramaService (ProgramaRepository programaRpt){
        this.programaRpt = programaRpt;
    }

    @Transactional
    public Resultado save(ProgramaModel programaMdl){

        programaRpt.save(programaMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado save(ProgramaDto programaDto, ProgramaModel programaMdl){

        Optional<LicencaModel> licencaModelOptional = licencaService.findById(programaDto.getLicencaId());

        if (licencaModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.licencaNaoEncontrada()
            );
        }

        programaMdl.setLicenca(licencaModelOptional.get());

        programaRpt.save(programaMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<ProgramaModel> findAll(Pageable pageable) {
        return programaRpt.findAll(pageable);
    }

    public Optional<ProgramaModel> findById(Integer id) {
        return programaRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<ProgramaModel> programaModelOptional = findById(id);

        if (programaModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.programaNaoEncontrado()
            );
        }

        programaRpt.delete(programaModelOptional.get());

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(ProgramaModel programaMdl){

        programaRpt.save(programaMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

}
