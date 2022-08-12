package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.EmprestimoModel;
import com.controleestoquensgio.dtos.EmprestimoDto;
import com.controleestoquensgio.services.EmprestimoService;

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
@RequestMapping(value = {"/controle-estoque/emprestimo"})
public class EmprestimoController extends ControllerFather{

    @Autowired
    EmprestimoService emprestimoSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EmprestimoDto emprestimoDto){
        var emprestimoModel = new EmprestimoModel();
        BeanUtils.copyProperties(emprestimoDto, emprestimoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoSvc.save(emprestimoModel));
    }

    @GetMapping
    public ResponseEntity<Page<EmprestimoModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<EmprestimoModel> emprestimoModelOptional = emprestimoSvc.findById(id);
        if(!emprestimoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<EmprestimoModel> emprestimoModelOptional = emprestimoSvc.findById(id);
        if(!emprestimoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado");
        }
        emprestimoSvc.delete(emprestimoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Emprestimo deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid EmprestimoDto emprestimoDto){
        Optional<EmprestimoModel> emprestimoModelOptional = emprestimoSvc.findById(id);
        if(!emprestimoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado");
        }

        var emprestimoModel = emprestimoModelOptional.get();
        BeanUtils.copyProperties(emprestimoDto, emprestimoModel);
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoSvc.save(emprestimoModel));
    }
}
