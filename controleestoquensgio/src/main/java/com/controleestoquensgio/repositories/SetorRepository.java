package com.controleestoquensgio.repositories;

import com.controleestoquensgio.models.SetorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, Integer>{
    Page<SetorModel> findAll(Pageable pageable);

    Page<SetorModel> findAllByNivel(Pageable pageable, String nivel);
}
