package com.controleestoquensgio.controllers;


import java.util.Optional;

import jakarta.validation.Valid;
import com.controleestoquensgio.models.TipoEquipamentoModel;
import com.controleestoquensgio.dtos.TipoEquipamentoDto;
import com.controleestoquensgio.services.TipoEquipamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/tiposDeEquipamento"})
public class TipoEquipamentoController extends ControllerFather {

    @Autowired
    TipoEquipamentoService tipoEquipamentoSvc;

    @PostMapping
    public String save(@Valid TipoEquipamentoDto tipoEquipamentoDto) {

        var tipoEquipamentoModel = new TipoEquipamentoModel();

        BeanUtils.copyProperties(tipoEquipamentoDto, tipoEquipamentoModel);

        tipoEquipamentoSvc.save(tipoEquipamentoModel);

        return "redirect:/tiposDeEquipamento";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = tipoEquipamentoSvc.findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) return "redirect:/tiposDeEquipamento";

        tipoEquipamentoSvc.delete(tipoEquipamentoModelOptional.get());

        return "redirect:/tiposDeEquipamento";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @RequestBody @Valid TipoEquipamentoDto tipoEquipamentoDto) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = tipoEquipamentoSvc.findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) return "redirect:/tiposDeEquipamento";

        var tipoEquipamentoModel = tipoEquipamentoModelOptional.get();

        BeanUtils.copyProperties(tipoEquipamentoDto, tipoEquipamentoModel);

        return "redirect:/tiposDeEquipamento";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        Iterable<TipoEquipamentoModel> tiposDeEquipamento = tipoEquipamentoSvc.findAll(pageable);

        model.addAttribute("tiposDeEquipamento", tiposDeEquipamento);

        return "listarTipoEquipamento";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = tipoEquipamentoSvc.findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de equipamento não encontrado");

        return ResponseEntity.status(HttpStatus.OK).body(tipoEquipamentoModelOptional.get());
    }

    @GetMapping("/cadastrar")
    public String exibirCadastrar(Model model) {

        TipoEquipamentoDto tipoDeEquipamento = new TipoEquipamentoDto();

        model.addAttribute("tipoDeEquipamento", tipoDeEquipamento);

        return "cadastrarTipoEquipamento";
    }
}
