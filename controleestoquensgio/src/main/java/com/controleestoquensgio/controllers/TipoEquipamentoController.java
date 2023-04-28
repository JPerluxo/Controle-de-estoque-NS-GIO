package com.controleestoquensgio.controllers;

import java.util.Optional;

import jakarta.validation.Valid;
import com.controleestoquensgio.models.TipoEquipamentoModel;
import com.controleestoquensgio.dtos.TipoEquipamentoDto;
import com.controleestoquensgio.services.TipoEquipamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/tiposDeEquipamento"})
public class TipoEquipamentoController extends ControllerFather {

    @Autowired
    TipoEquipamentoService tipoEquipamentoSvc;

    @PostMapping
    public String save(@Valid TipoEquipamentoDto tipoEquipamentoDto, BindingResult result, Model model, Pageable pageable) {

        if (result.hasErrors()) {
            model.addAttribute("tipoDeEquipamento", tipoEquipamentoDto);
            model.addAttribute("tiposDeEquipamento", tipoEquipamentoSvc.findAll(pageable));
            return "cadastrarTipoEquipamento";
        }

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
    public String update(@PathVariable(value = "id") int id, @Valid TipoEquipamentoDto tipoEquipamentoDto) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = tipoEquipamentoSvc.findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) return "redirect:/tiposDeEquipamento";

        var tipoEquipamentoModel = tipoEquipamentoModelOptional.get();

        BeanUtils.copyProperties(tipoEquipamentoDto, tipoEquipamentoModel);

        tipoEquipamentoSvc.save(tipoEquipamentoModel);

        return "redirect:/tiposDeEquipamento";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        Iterable<TipoEquipamentoModel> tiposDeEquipamento = tipoEquipamentoSvc.findAll(pageable);
        TipoEquipamentoDto tipoDeEquipamento = new TipoEquipamentoDto();

        model.addAttribute("tiposDeEquipamento", tiposDeEquipamento);
        model.addAttribute("tipoDeEquipamento", tipoDeEquipamento);

        return "cadastrarTipoEquipamento";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = tipoEquipamentoSvc.findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) return "redirect:/tiposDeEquipamento";

        model.addAttribute("tipoDeEquipamento", tipoEquipamentoModelOptional.get());

        return "atualizarTipoEquipamento";
    }
}
