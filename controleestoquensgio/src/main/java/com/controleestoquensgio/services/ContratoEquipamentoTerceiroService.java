package com.controleestoquensgio.services;

import java.util.Optional;

import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.repositories.ContratoEquipamentoTerceiroRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContratoEquipamentoTerceiroService {
    final ContratoEquipamentoTerceiroRepository contratoEquipamentoTerceiroRpt;

    public ContratoEquipamentoTerceiroService (ContratoEquipamentoTerceiroRepository contratoEquipamentoTerceiroRpt){
        this.contratoEquipamentoTerceiroRpt = contratoEquipamentoTerceiroRpt;
    }

    @Transactional
    public ContratoEquipamentoTerceiroModel save(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroMdl){
        return contratoEquipamentoTerceiroRpt.save(contratoEquipamentoTerceiroMdl);
    } 

    public Page<ContratoEquipamentoTerceiroModel> findAll(Pageable pageable) {
        return contratoEquipamentoTerceiroRpt.findAll(pageable);
    }

    public Optional<ContratoEquipamentoTerceiroModel> findById(Integer id) {
        return contratoEquipamentoTerceiroRpt.findById(id);
    }

    @Transactional
    public void delete(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroMdl) {
        contratoEquipamentoTerceiroRpt.delete(contratoEquipamentoTerceiroMdl);
    }
}
