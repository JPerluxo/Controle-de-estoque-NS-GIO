package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.RegimeTrabalhoModel;
import com.controleestoquensgio.repositories.RegimeTrabalhoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegimeTrabalhoService {
    final RegimeTrabalhoRepository regimeTrabalhoRpt;

    public RegimeTrabalhoService (RegimeTrabalhoRepository regimeTrabalhoRpt){
        this.regimeTrabalhoRpt = regimeTrabalhoRpt;
    }

    @Transactional
    public RegimeTrabalhoModel save(RegimeTrabalhoModel regimeTrabalhoMdl){
        return regimeTrabalhoRpt.save(regimeTrabalhoMdl);
    } 

    public Page<RegimeTrabalhoModel> findAll(Pageable pageable) {
        return regimeTrabalhoRpt.findAll(pageable);
    }

    public Optional<RegimeTrabalhoModel> findById(Integer id) {
        return regimeTrabalhoRpt.findById(id);
    }

    @Transactional
    public void delete(RegimeTrabalhoModel regimeTrabalhoMdl) {
        regimeTrabalhoRpt.delete(regimeTrabalhoMdl);
    }
}
