package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.GerenciaModel;
import com.controleestoquensgio.repositories.GerenciaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GerenciaService {
    final GerenciaRepository gerenciaRpt;

    public GerenciaService (GerenciaRepository gerenciaRpt){
        this.gerenciaRpt = gerenciaRpt;
    }

    @Transactional
    public GerenciaModel save(GerenciaModel gerenciaMdl){
        return gerenciaRpt.save(gerenciaMdl);
    } 

    public Page<GerenciaModel> findAll(Pageable pageable) {
        return gerenciaRpt.findAll(pageable);
    }

    public Optional<GerenciaModel> findById(Integer id) {
        return gerenciaRpt.findById(id);
    }

    @Transactional
    public void delete(GerenciaModel gerenciaMdl) {
        gerenciaRpt.delete(gerenciaMdl);
    }
}
