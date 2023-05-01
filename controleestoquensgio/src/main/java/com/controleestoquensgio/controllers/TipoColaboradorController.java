package com.controleestoquensgio.controllers;

import java.util.Optional;

import jakarta.validation.Valid;

import com.controleestoquensgio.dtos.TipoColaboradorDto;
import com.controleestoquensgio.models.TipoColaboradorModel;
import com.controleestoquensgio.services.TipoColaboradorService;
import com.controleestoquensgio.util.Mensagens;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/tiposDeColaborador"})
public class TipoColaboradorController extends ControllerFather{

    @Autowired
    TipoColaboradorService tipoColaboradorService;

    @PostMapping
    public String save(@Valid TipoColaboradorDto tipoColaboradorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("tipoColaboradorDto", tipoColaboradorDto);
            model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorService.findAll(pageable));
            return "tipoColaborador/cadastrarTipoColaborador";
        }

        var tipoColaboradorModel = new TipoColaboradorModel();

        BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);

        var resultado = tipoColaboradorService.save(tipoColaboradorModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeColaborador";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = tipoColaboradorService.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeColaborador";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid TipoColaboradorDto tipoColaboradorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("tipoColaboradorDto", tipoColaboradorDto);
            model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorService.findAll(pageable));
            return "tipoColaborador/atualizarTipoColaborador";
        }

        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorService.findById(id);

        if (tipoColaboradorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    Mensagens.tipoDeColaboradorNaoEncontrado(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );

            return "redirect:/tiposDeColaborador";
        }

        var tipoColaboradorModel = tipoColaboradorModelOptional.get();

        BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);

        var resultado = tipoColaboradorService.update(tipoColaboradorModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeColaborador";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        Iterable<TipoColaboradorModel> listaDeTiposDeEquipamento = tipoColaboradorService.findAll(pageable);
        TipoColaboradorDto tipodeColaborador = new TipoColaboradorDto();

        model.addAttribute("listaDeTiposDeColaborador", listaDeTiposDeEquipamento);
        model.addAttribute("tipoColaboradorDto", tipodeColaborador);

        return "tipoColaborador/cadastrarTipoColaborador";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorService.findById(id);

        if (tipoColaboradorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    Mensagens.tipoDeColaboradorNaoEncontradoTipoDeMensagem(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );

            return "redirect:/tiposDeColaborador";
        }

        model.addAttribute("tipoColaboradorDto", tipoColaboradorModelOptional.get());

        return "tipoColaborador/atualizarTipoColaborador";
    }
}
