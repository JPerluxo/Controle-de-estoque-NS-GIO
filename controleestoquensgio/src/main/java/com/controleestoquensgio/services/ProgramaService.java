package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.ProgramaModel;
import com.controleestoquensgio.repositories.ProgramaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProgramaService {
    final ProgramaRepository programaRpt;

    public ProgramaService (ProgramaRepository programaRpt){
        this.programaRpt = programaRpt;
    }

    @Transactional
    public ProgramaModel save(ProgramaModel programaMdl){
        return programaRpt.save(programaMdl);
    } 

    public Page<ProgramaModel> findAll(Pageable pageable) {
        return programaRpt.findAll(pageable);
    }

    public Optional<ProgramaModel> findById(Integer id) {
        return programaRpt.findById(id);
    }

    @Transactional
    public void delete(ProgramaModel programaMdl) {
        programaRpt.delete(programaMdl);
    }
}
