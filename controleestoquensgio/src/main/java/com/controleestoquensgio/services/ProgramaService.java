package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.programa.ProgramaDto;
import com.controleestoquensgio.dtos.programa.FiltrarProgramaDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.models.ProgramaModel;
import com.controleestoquensgio.repositories.ProgramaQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.ProgramaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProgramaService {

    @Autowired
    LicencaService licencaService;

    final ProgramaRepository programaRpt;

    final ProgramaQueryRepository programaQueryRpt;

    public ProgramaService (ProgramaRepository programaRpt, ProgramaQueryRepository programaQueryRpt){
        this.programaRpt = programaRpt;
        this.programaQueryRpt = programaQueryRpt;
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

    public Page<ProgramaModel> findAllAtivo(Pageable pageable, String ativo) {
        return programaRpt.findAllByAtivo(pageable, ativo);
    }

    public Page<ProgramaModel> findAllByFilter(Pageable pageable, FiltrarProgramaDto filtrarProgramaDto) {

        Optional<LicencaModel> licencaModelOptional = licencaService.findById(filtrarProgramaDto.getLicencaId());
        licencaModelOptional.ifPresent(filtrarProgramaDto::setLicencaModel);

        var programas = programaQueryRpt.customQuery(filtrarProgramaDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), programas.size());

        return new PageImpl<>(programas.subList(start, end), pageable, programas.size());
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

        ProgramaModel programaModel = programaModelOptional.get();
        programaModel.setAtivo(SimOuNao.NAO.name());

        programaRpt.save(programaModel);

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
