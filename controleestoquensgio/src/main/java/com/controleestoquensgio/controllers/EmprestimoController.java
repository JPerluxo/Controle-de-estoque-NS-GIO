package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.colaborador.ListarColaboradoresDto;
import com.controleestoquensgio.dtos.emprestimo.EmprestimoDto;
import com.controleestoquensgio.dtos.emprestimo.ListarEmprestimosDto;
import com.controleestoquensgio.dtos.emprestimo.VisualizarEmprestimoDto;
import com.controleestoquensgio.dtos.equipamento.ListarEquipamentosDto;
import com.controleestoquensgio.dtos.emprestimo.FiltrarEmprestimoDto;
import com.controleestoquensgio.models.EmprestimoModel;
import com.controleestoquensgio.services.ColaboradorService;
import com.controleestoquensgio.services.EmprestimoService;
import com.controleestoquensgio.services.EquipamentoService;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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
            model.addAttribute("listaDeEmprestimos", emprestimoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEmprestimosDto::new));
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEquipamentosDto::new));
            model.addAttribute("filtrarEmprestimoDto", new FiltrarEmprestimoDto());
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
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEquipamentosDto::new));
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
        model.addAttribute("listaDeEmprestimos", emprestimoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEmprestimosDto::new));
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEquipamentosDto::new));
        model.addAttribute("filtrarEmprestimoDto", new FiltrarEmprestimoDto());

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
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEquipamentosDto::new));

        return "emprestimo/atualizarEmprestimo";
    }

    @PostMapping("/filtrar")
    public String filter(Pageable pageable, Model model, FiltrarEmprestimoDto filtrarEmprestimoDto) {

        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEquipamentosDto::new));
        model.addAttribute("listaDeEmprestimos", emprestimoSvc.findAllByFilter(pageable, filtrarEmprestimoDto).map(ListarEmprestimosDto::new));
        model.addAttribute("emprestimoDto", new EmprestimoDto());
        model.addAttribute("filtrarEmprestimoDto", new FiltrarEmprestimoDto());

        return "emprestimo/cadastrarEmprestimo";
    }
}
