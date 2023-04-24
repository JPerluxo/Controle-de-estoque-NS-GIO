package com.controleestoquensgio.services;

import java.util.Optional;

import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.DiretoriaModel;
import com.controleestoquensgio.repositories.DiretoriaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DiretoriaService {
    final DiretoriaRepository diretoriaRpt;

    public DiretoriaService (DiretoriaRepository diretoriaRpt){
        this.diretoriaRpt = diretoriaRpt;
    }

    @Transactional
    public DiretoriaModel save(DiretoriaModel diretoriaMdl){
        return diretoriaRpt.save(diretoriaMdl);
    } 

    public Page<DiretoriaModel> findAll(Pageable pageable) {
        return diretoriaRpt.findAll(pageable);
    }

    public Optional<DiretoriaModel> findById(Integer id) {
        return diretoriaRpt.findById(id);
    }

    @Transactional
    public void delete(DiretoriaModel diretoriaMdl) {
        diretoriaRpt.delete(diretoriaMdl);
    }
}
