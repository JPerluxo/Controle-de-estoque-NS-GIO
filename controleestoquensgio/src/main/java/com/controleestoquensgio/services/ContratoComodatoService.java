package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.ContratoComodatoModel;
import com.controleestoquensgio.repositories.ContratoComodatoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContratoComodatoService {
    final ContratoComodatoRepository contratoComodatoRpt;

    public ContratoComodatoService (ContratoComodatoRepository contratoComodatoRpt){
        this.contratoComodatoRpt = contratoComodatoRpt;
    }

    @Transactional
    public ContratoComodatoModel save(ContratoComodatoModel contratoComodatoMdl){
        return contratoComodatoRpt.save(contratoComodatoMdl);
    } 

    public Page<ContratoComodatoModel> findAll(Pageable pageable) {
        return contratoComodatoRpt.findAll(pageable);
    }

    public Optional<ContratoComodatoModel> findById(Integer id) {
        return contratoComodatoRpt.findById(id);
    }

    @Transactional
    public void delete(ContratoComodatoModel contratoComodatoMdl) {
        contratoComodatoRpt.delete(contratoComodatoMdl);
    }
}
