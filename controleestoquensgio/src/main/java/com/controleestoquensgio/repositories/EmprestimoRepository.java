package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.EmprestimoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoModel, Integer>{
    Page<EmprestimoModel> findAll(Pageable pageable);

    Page<EmprestimoModel> findAllByAtivo(Pageable pageable, String ativo);

}
