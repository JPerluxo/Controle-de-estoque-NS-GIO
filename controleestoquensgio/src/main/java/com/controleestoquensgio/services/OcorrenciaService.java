package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.OcorrenciaModel;
import com.controleestoquensgio.repositories.OcorrenciaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaService {
    final OcorrenciaRepository ocorrenciaRpt;

    public OcorrenciaService (OcorrenciaRepository ocorrenciaRpt){
        this.ocorrenciaRpt = ocorrenciaRpt;
    }

    @Transactional
    public OcorrenciaModel save(OcorrenciaModel ocorrenciaMdl){
        return ocorrenciaRpt.save(ocorrenciaMdl);
    } 

    public Page<OcorrenciaModel> findAll(Pageable pageable) {
        return ocorrenciaRpt.findAll(pageable);
    }

    public Optional<OcorrenciaModel> findById(Integer id) {
        return ocorrenciaRpt.findById(id);
    }

    @Transactional
    public void delete(OcorrenciaModel ocorrenciaMdl) {
        ocorrenciaRpt.delete(ocorrenciaMdl);
    }
}
