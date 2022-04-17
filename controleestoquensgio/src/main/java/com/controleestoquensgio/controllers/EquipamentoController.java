package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.dtos.EquipamentoDto;
import com.controleestoquensgio.services.EquipamentoService;

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
@RequestMapping(value = {"/controle-estoque/equipamento"})
public class EquipamentoController {

    @Autowired
    EquipamentoService equipamentoSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EquipamentoDto equipamentoDto){
        var equipamentoModel = new EquipamentoModel();
        BeanUtils.copyProperties(equipamentoDto, equipamentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoSvc.save(equipamentoModel));
    }

    @GetMapping
    public ResponseEntity<Page<EquipamentoModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(equipamentoSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(id);
        if(!equipamentoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(equipamentoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(id);
        if(!equipamentoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento não encontrado");
        }
        equipamentoSvc.delete(equipamentoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Equipamento deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid EquipamentoDto equipamentoDto){
        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(id);
        if(!equipamentoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento não encontrado");
        }

        var equipamentoModel = equipamentoModelOptional.get();
        BeanUtils.copyProperties(equipamentoDto, equipamentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(equipamentoSvc.save(equipamentoModel));
    }
}
