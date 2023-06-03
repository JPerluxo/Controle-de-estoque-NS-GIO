package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.tipoEquipamento.FiltrarTipoEquipamentoDto;
import com.controleestoquensgio.dtos.tipoEquipamento.ListarTipoEquipamentoDto;
import com.controleestoquensgio.dtos.tipoEquipamento.TipoEquipamentoDto;
import com.controleestoquensgio.dtos.tipoEquipamento.VisualizarTipoEquipamentoDto;
import com.controleestoquensgio.models.TipoEquipamentoModel;
import com.controleestoquensgio.services.TipoEquipamentoService;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = {"/tiposDeEquipamento"})
public class TipoEquipamentoController {

    @Autowired
    TipoEquipamentoService tipoEquipamentoSvc;

    @PostMapping
    public String save(@Valid TipoEquipamentoDto tipoEquipamentoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("tipoEquipamentoDto", tipoEquipamentoDto);
            model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoEquipamentoDto::new));
            model.addAttribute("filtrarTipoEquipamentoDto", new FiltrarTipoEquipamentoDto());
            return "tipoEquipamento/cadastrarTipoEquipamento";
        }

        var tipoEquipamentoModel = new TipoEquipamentoModel();

        BeanUtils.copyProperties(tipoEquipamentoDto, tipoEquipamentoModel);

        var resultado = tipoEquipamentoSvc.save(tipoEquipamentoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeEquipamento";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = tipoEquipamentoSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeEquipamento";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid TipoEquipamentoDto tipoEquipamentoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("tipoEquipamentoDto", tipoEquipamentoDto);
            return "tipoEquipamento/atualizarTipoEquipamento";
        }

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = tipoEquipamentoSvc.findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/tiposDeEquipamento";
        }

        var tipoEquipamentoModel = tipoEquipamentoModelOptional.get();

        BeanUtils.copyProperties(tipoEquipamentoDto, tipoEquipamentoModel);

        var resultado = tipoEquipamentoSvc.update(tipoEquipamentoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeEquipamento";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoEquipamentoDto::new));
        model.addAttribute("tipoEquipamentoDto", new TipoEquipamentoDto());
        model.addAttribute("filtrarTipoEquipamentoDto", new FiltrarTipoEquipamentoDto());

        return "tipoEquipamento/cadastrarTipoEquipamento";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOptional = tipoEquipamentoSvc.findById(id);

        if (tipoEquipamentoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/tiposDeEquipamento";
        }

        model.addAttribute("tipoEquipamentoDto", new VisualizarTipoEquipamentoDto(tipoEquipamentoModelOptional.get()));

        return "tipoEquipamento/atualizarTipoEquipamento";
    }

    @PostMapping("/filtrar")
    public String filter(Pageable pageable, Model model, FiltrarTipoEquipamentoDto filtrarTipoEquipamentoDto) {

        model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllByFilter(pageable, filtrarTipoEquipamentoDto).map(ListarTipoEquipamentoDto::new));
        model.addAttribute("tipoEquipamentoDto", new TipoEquipamentoDto());
        model.addAttribute("filtrarTipoEquipamentoDto", new FiltrarTipoEquipamentoDto());

        return "tipoEquipamento/cadastrarTipoEquipamento";
    }
}
