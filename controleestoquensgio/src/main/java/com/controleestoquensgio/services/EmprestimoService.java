package com.controleestoquensgio.services;

import java.util.Optional;

import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.EmprestimoModel;
import com.controleestoquensgio.repositories.EmprestimoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {
    final EmprestimoRepository emprestimoRpt;

    public EmprestimoService (EmprestimoRepository emprestimoRpt){
        this.emprestimoRpt = emprestimoRpt;
    }

    @Transactional
    public EmprestimoModel save(EmprestimoModel emprestimoMdl){
        return emprestimoRpt.save(emprestimoMdl);
    } 

    public Page<EmprestimoModel> findAll(Pageable pageable) {
        return emprestimoRpt.findAll(pageable);
    }

    public Optional<EmprestimoModel> findById(Integer id) {
        return emprestimoRpt.findById(id);
    }

    @Transactional
    public void delete(EmprestimoModel emprestimoMdl) {
        emprestimoRpt.delete(emprestimoMdl);
    }
}
