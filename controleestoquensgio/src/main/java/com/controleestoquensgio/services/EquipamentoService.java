package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.repositories.EquipamentoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {
    final EquipamentoRepository equipamentoRpt;

    public EquipamentoService (EquipamentoRepository equipamentoRpt){
        this.equipamentoRpt = equipamentoRpt;
    }

    @Transactional
    public EquipamentoModel save(EquipamentoModel equipamentoMdl){
        return equipamentoRpt.save(equipamentoMdl);
    } 

    public Page<EquipamentoModel> findAll(Pageable pageable) {
        return equipamentoRpt.findAll(pageable);
    }

    public Optional<EquipamentoModel> findById(Integer id) {
        return equipamentoRpt.findById(id);
    }

    @Transactional
    public void delete(EquipamentoModel equipamentoMdl) {
        equipamentoRpt.delete(equipamentoMdl);
    }
}
