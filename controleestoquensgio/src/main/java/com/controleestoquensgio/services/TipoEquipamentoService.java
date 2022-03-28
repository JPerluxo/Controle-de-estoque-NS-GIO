package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.TipoEquipamentoModel;
import com.controleestoquensgio.repositories.TipoEquipamentoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoEquipamentoService {
    final TipoEquipamentoRepository tipoEquipamentoRpt;

    public TipoEquipamentoService (TipoEquipamentoRepository tipoEquipamentoRpt){
        this.tipoEquipamentoRpt = tipoEquipamentoRpt;
    }

    @Transactional
    public TipoEquipamentoModel save(TipoEquipamentoModel tipoEquipamentoMdl){
        return tipoEquipamentoRpt.save(tipoEquipamentoMdl);
    } 

    public Page<TipoEquipamentoModel> findAll(Pageable pageable) {
        return tipoEquipamentoRpt.findAll(pageable);
    }

    public Optional<TipoEquipamentoModel> findById(Integer id) {
        return tipoEquipamentoRpt.findById(id);
    }

    @Transactional
    public void delete(TipoEquipamentoModel tipoEquipamentoMdl) {
        tipoEquipamentoRpt.delete(tipoEquipamentoMdl);
    }
}
