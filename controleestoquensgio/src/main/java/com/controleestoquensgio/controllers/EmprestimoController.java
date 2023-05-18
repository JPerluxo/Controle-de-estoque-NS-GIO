package com.controleestoquensgio.controllers;

import java.util.Optional;


import com.controleestoquensgio.dtos.emprestimo.EmprestimoDto;
import com.controleestoquensgio.dtos.colaborador.ListarColaboradoresDto;
import com.controleestoquensgio.dtos.emprestimo.ListarEmprestimosDto;
import com.controleestoquensgio.dtos.emprestimo.VisualizarEmprestimoDto;
import com.controleestoquensgio.dtos.equipamento.ListarEquipamentosDto;
import com.controleestoquensgio.models.EmprestimoModel;
import com.controleestoquensgio.services.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/emprestimos"})
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoSvc;

    @Autowired
    ColaboradorService colaboradorSvc;

    @Autowired
    EquipamentoService equipamentoSvc;


    @PostMapping
    public String save(@Valid EmprestimoDto emprestimoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("emprestimoDto", emprestimoDto);
            model.addAttribute("listaDeEmprestimos", emprestimoSvc.findAll(pageable).map(ListarEmprestimosDto::new));
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
            return "emprestimo/cadastrarEmprestimo";
        }

        var resultado = emprestimoSvc.save(emprestimoDto, new EmprestimoModel());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/emprestimos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = emprestimoSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/emprestimos";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid EmprestimoDto emprestimoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("emprestimoDto", emprestimoDto);
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));
            return "emprestimo/atualizarEmprestimo";
        }


        Optional<EmprestimoModel> emprestimoModelOptional = emprestimoSvc.findById(id);

        if (emprestimoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.emprestimoNaoEncontrado()
            );

            return "redirect:/emprestimos";
        }

        var resultado = emprestimoSvc.save(emprestimoDto, emprestimoModelOptional.get());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/emprestimos";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("emprestimoDto", new EmprestimoDto());
        model.addAttribute("listaDeEmprestimos", emprestimoSvc.findAll(pageable).map(ListarEmprestimosDto::new));
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));

        return "emprestimo/cadastrarEmprestimo";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {

        Optional<EmprestimoModel> emprestimoModelOptional = emprestimoSvc.findById(id);

        if (emprestimoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.emprestimoNaoEncontrado()
            );

            return "redirect:/emprestimos";
        }

        model.addAttribute("emprestimoDto", new VisualizarEmprestimoDto(emprestimoModelOptional.get()));
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAll(pageable).map(ListarEquipamentosDto::new));

        return "emprestimo/atualizarEmprestimo";
    }
}
