package com.controleestoquensgio.services;

import java.util.Optional;

import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.PresidenciaModel;
import com.controleestoquensgio.repositories.PresidenciaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PresidenciaService {
    final PresidenciaRepository presidenciaRpt;

    public PresidenciaService (PresidenciaRepository presidenciaRpt){
        this.presidenciaRpt = presidenciaRpt;
    }

    @Transactional
    public PresidenciaModel save(PresidenciaModel presidenciaMdl){
        return presidenciaRpt.save(presidenciaMdl);
    } 

    public Page<PresidenciaModel> findAll(Pageable pageable) {
        return presidenciaRpt.findAll(pageable);
    }

    public Optional<PresidenciaModel> findById(Integer id) {
        return presidenciaRpt.findById(id);
    }

    @Transactional
    public void delete(PresidenciaModel presidenciaMdl) {
        presidenciaRpt.delete(presidenciaMdl);
    }
}
