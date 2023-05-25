package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.contratoEquipamentoTerceiro.ContratoEquipamentoTerceiroDto;
import com.controleestoquensgio.dtos.contratoEquipamentoTerceiro.ListarContratoEquipamentoTerceiroDto;
import com.controleestoquensgio.dtos.contratoEquipamentoTerceiro.VisualizarContratoEquipamentoTerceiroDto;
import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.services.ContratoEquipamentoTerceiroService;
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

@Controller
@RequestMapping(value = {"/contratosDeEquipamentoDeTerceiro"})
public class ContratoEquipamentoTerceiroController {

    @Autowired
    ContratoEquipamentoTerceiroService contratoEquipamentoTerceiroService;

    @PostMapping
    public String save(@Valid ContratoEquipamentoTerceiroDto contratoEquipamentoTerceiroDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("contratoEquipamentoTerceiroDto", contratoEquipamentoTerceiroDto);
            model.addAttribute("listaDeContratosDeEquipamentoDeTerceiro", contratoEquipamentoTerceiroService.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));
            return "contratoEquipamentoTerceiro/cadastrarContratoEquipamentoTerceiro";
        }

        var contratoEquipamentoTerceiroModel = new ContratoEquipamentoTerceiroModel();

        BeanUtils.copyProperties(contratoEquipamentoTerceiroDto, contratoEquipamentoTerceiroModel);

        contratoEquipamentoTerceiroModel.setDataInicio(new java.sql.Date(contratoEquipamentoTerceiroDto.getDataInicio().getTime()));
        contratoEquipamentoTerceiroModel.setDataFinal(new java.sql.Date(contratoEquipamentoTerceiroDto.getDataFinal().getTime()));

        var resultado = contratoEquipamentoTerceiroService.save(contratoEquipamentoTerceiroModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/contratosDeEquipamentoDeTerceiro";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = contratoEquipamentoTerceiroService.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/contratosDeEquipamentoDeTerceiro";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid ContratoEquipamentoTerceiroDto contratoEquipamentoTerceiroDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("contratoEquipamentoTerceiroDto", contratoEquipamentoTerceiroDto);
            model.addAttribute("listaDeContratosDeEquipamentoDeTerceiro", contratoEquipamentoTerceiroService.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));
            return "contratoEquipamentoTerceiro/atualizarContratoEquipamentoTerceiro";
        }

        Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = contratoEquipamentoTerceiroService.findById(id);

        if (contratoEquipamentoTerceiroModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.contratoDeEquipamentoDeTerceiroNaoEncontrado()
            );

            return "redirect:/contratosDeEquipamentoDeTerceiro";
        }

        var contratoEquipamentoTerceiroModel = contratoEquipamentoTerceiroModelOptional.get();

        BeanUtils.copyProperties(contratoEquipamentoTerceiroDto, contratoEquipamentoTerceiroModel);

        contratoEquipamentoTerceiroModel.setDataInicio(new java.sql.Date(contratoEquipamentoTerceiroDto.getDataInicio().getTime()));
        contratoEquipamentoTerceiroModel.setDataFinal(new java.sql.Date(contratoEquipamentoTerceiroDto.getDataFinal().getTime()));

        var resultado = contratoEquipamentoTerceiroService.update(contratoEquipamentoTerceiroModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/contratosDeEquipamentoDeTerceiro";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeContratosDeEquipamentoDeTerceiro", contratoEquipamentoTerceiroService.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));
        model.addAttribute("contratoEquipamentoTerceiroDto", new ContratoEquipamentoTerceiroDto());

        return "contratoEquipamentoTerceiro/cadastrarContratoEquipamentoTerceiro";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = contratoEquipamentoTerceiroService.findById(id);

        if (contratoEquipamentoTerceiroModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.contratoDeEquipamentoDeTerceiroNaoEncontrado()
            );

            return "redirect:/contratosDeEquipamentoDeTerceiro";
        }

        model.addAttribute("contratoEquipamentoTerceiroDto", new VisualizarContratoEquipamentoTerceiroDto(contratoEquipamentoTerceiroModelOptional.get()));

        return "contratoEquipamentoTerceiro/atualizarContratoEquipamentoTerceiro";
    }
}
