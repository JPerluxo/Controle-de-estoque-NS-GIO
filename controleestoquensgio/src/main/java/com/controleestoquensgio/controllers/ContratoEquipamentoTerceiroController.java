package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.dtos.ContratoEquipamentoTerceiroDto;
import com.controleestoquensgio.services.ContratoEquipamentoTerceiroService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping(value = {"/controle-estoque/contratoEquipamentoTerceiro"})
public class ContratoEquipamentoTerceiroController {

    @Autowired
    ContratoEquipamentoTerceiroService contratoEquipamentoTerceiroSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ContratoEquipamentoTerceiroDto contratoEquipamentoTerceiroDto){
        
        var contratoEquipamentoTerceiroModel = new ContratoEquipamentoTerceiroModel();
        BeanUtils.copyProperties(contratoEquipamentoTerceiroDto, contratoEquipamentoTerceiroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoEquipamentoTerceiroSvc.save(contratoEquipamentoTerceiroModel));
    }

    @GetMapping
    public ResponseEntity<Page<ContratoEquipamentoTerceiroModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contratoEquipamentoTerceiroSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = contratoEquipamentoTerceiroSvc.findById(id);
        if(!contratoEquipamentoTerceiroModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contratoEquipamentoTerceiroModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = contratoEquipamentoTerceiroSvc.findById(id);
        if(!contratoEquipamentoTerceiroModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
        }
        contratoEquipamentoTerceiroSvc.delete(contratoEquipamentoTerceiroModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contrato deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid ContratoEquipamentoTerceiroDto contratoEquipamentoTerceiroDto){
        Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = contratoEquipamentoTerceiroSvc.findById(id);
        if(!contratoEquipamentoTerceiroModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
        }

        var contratoEquipamentoTerceiroModel = contratoEquipamentoTerceiroModelOptional.get();
        BeanUtils.copyProperties(contratoEquipamentoTerceiroDto, contratoEquipamentoTerceiroModel);
        return ResponseEntity.status(HttpStatus.OK).body(contratoEquipamentoTerceiroSvc.save(contratoEquipamentoTerceiroModel));
    }
}
