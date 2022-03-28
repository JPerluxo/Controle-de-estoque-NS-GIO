package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.repositories.ColaboradorRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    final ColaboradorRepository colaboradorRpt;

    public ColaboradorService (ColaboradorRepository colaboradorRpt){
        this.colaboradorRpt = colaboradorRpt;
    }

    @Transactional
    public ColaboradorModel save(ColaboradorModel colaboradorMdl){
        return colaboradorRpt.save(colaboradorMdl);
    } 

    public Page<ColaboradorModel> findAll(Pageable pageable) {
        return colaboradorRpt.findAll(pageable);
    }

    public Optional<ColaboradorModel> findById(Integer id) {
        return colaboradorRpt.findById(id);
    }

    @Transactional
    public void delete(ColaboradorModel colaboradorMdl) {
        colaboradorRpt.delete(colaboradorMdl);
    }
}
