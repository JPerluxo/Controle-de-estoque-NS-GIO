package com.controleestoquensgio.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.controleestoquensgio.dtos.ProgramaDto;
import com.controleestoquensgio.models.ProgramaModel;
import com.controleestoquensgio.services.ProgramaService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping(value = {"/controle-estoque/programa", "/"})
public class ProgramaController {

    final ProgramaService programaSvc;

    public ProgramaController(ProgramaService programaSvc) {
        this.programaSvc = programaSvc;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProgramaDto programaDto){
        
        var programaModel = new ProgramaModel();
        BeanUtils.copyProperties(programaDto, programaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(programaSvc.save(programaModel));
    }

    @GetMapping
    public ResponseEntity<Page<ProgramaModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(programaSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<ProgramaModel> programaModelOptional = programaSvc.findById(id);
        if(!programaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Programa not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(programaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<ProgramaModel> programaModelOptional = programaSvc.findById(id);
        if(!programaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Programa not found");
        }
        programaSvc.delete(programaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Programa deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid ProgramaDto programaDto){
        Optional<ProgramaModel> programaModelOptional = programaSvc.findById(id);
        if(!programaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Programa not found");
        }

        var programaModel = programaModelOptional.get();
        programaModel.setDescricao(programaDto.getDescricao());
        programaModel.setObservacao(programaDto.getObservacao());

        return ResponseEntity.status(HttpStatus.OK).body(programaSvc.save(programaModel));
    }
}
