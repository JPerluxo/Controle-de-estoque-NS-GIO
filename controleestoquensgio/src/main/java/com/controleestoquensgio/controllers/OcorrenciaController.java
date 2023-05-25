package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.colaborador.ListarColaboradoresDto;
import com.controleestoquensgio.dtos.equipamento.ListarEquipamentosDto;
import com.controleestoquensgio.dtos.ocorrencia.ListarOcorrenciasDto;
import com.controleestoquensgio.dtos.ocorrencia.OcorrenciaDto;
import com.controleestoquensgio.dtos.ocorrencia.VisualizarOcorrenciaDto;
import com.controleestoquensgio.models.OcorrenciaModel;
import com.controleestoquensgio.services.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.validation.Valid;
import com.controleestoquensgio.services.OcorrenciaService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/ocorrencias"})
public class OcorrenciaController {

    @Autowired
    OcorrenciaService ocorrenciaSvc;

    @Autowired
    EquipamentoService equipamentoSvc;

    @Autowired
    ColaboradorService colaboradorSvc;

    @PostMapping
    public String save(@Valid OcorrenciaDto ocorrenciaDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("ocorrenciaDto", ocorrenciaDto);
            model.addAttribute("listaDeOcorrencias", ocorrenciaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarOcorrenciasDto::new));
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
            return "ocorrencia/cadastrarOcorrencia";
        }

        var ocorrenciaModel = new OcorrenciaModel();

        BeanUtils.copyProperties(ocorrenciaDto, ocorrenciaModel);

        var resultado = ocorrenciaSvc.save(ocorrenciaDto, ocorrenciaModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/ocorrencias";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = ocorrenciaSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/ocorrencias";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid OcorrenciaDto ocorrenciaDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("ocorrenciaDto", ocorrenciaDto);
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
            return "ocorrencia/atualizarOcorrencia";
        }

        Optional<OcorrenciaModel> ocorrenciaModelOptional = ocorrenciaSvc.findById(id);

        if (ocorrenciaModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.ocorrenciaNaoEncontrada()
            );

            return "redirect:/ocorrencias";
        }

        var ocorrenciaModel = ocorrenciaModelOptional.get();

        BeanUtils.copyProperties(ocorrenciaDto, ocorrenciaModel);

        var resultado = ocorrenciaSvc.save(ocorrenciaDto, ocorrenciaModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/ocorrencias";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("ocorrenciaDto", new OcorrenciaDto());
        model.addAttribute("listaDeOcorrencias", ocorrenciaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarOcorrenciasDto::new));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));

        return "ocorrencia/cadastrarOcorrencia";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {

        Optional<OcorrenciaModel> ocorrenciaModelOptional = ocorrenciaSvc.findById(id);

        if (ocorrenciaModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.ocorrenciaNaoEncontrada()
            );

            return "redirect:/ocorrencias";
        }

        model.addAttribute("ocorrenciaDto", new VisualizarOcorrenciaDto(ocorrenciaModelOptional.get()));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));

        return "ocorrencia/atualizarOcorrencia";
    }
}
