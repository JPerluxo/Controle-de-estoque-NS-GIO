package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.contratoEquipamentoTerceiro.ListarContratoEquipamentoTerceiroDto;
import com.controleestoquensgio.dtos.equipamento.EquipamentoDto;
import com.controleestoquensgio.dtos.equipamento.FiltrarEquipamentoDto;
import com.controleestoquensgio.dtos.equipamento.ListarEquipamentosDto;
import com.controleestoquensgio.dtos.equipamento.VisualizarEquipamentoDto;
import com.controleestoquensgio.dtos.localizacao.ListarLocalizacaoDto;
import com.controleestoquensgio.dtos.notaFiscal.ListarNotaFiscalDto;
import com.controleestoquensgio.dtos.tipoEquipamento.ListarTipoEquipamentoDto;
import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.services.*;
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
@RequestMapping(value = {"/equipamentos"})
public class EquipamentoController {

    @Autowired
    EquipamentoService equipamentoSvc;

    @Autowired
    TipoEquipamentoService tipoEquipamentoSvc;

    @Autowired
    NotaFiscalService notaFiscalSvc;

    @Autowired
    LocalizacaoService localizacaoSvc;

    @Autowired
    ContratoEquipamentoTerceiroService contratoEquipamentoTerceiroSvc;

    @PostMapping
    public String save(@Valid EquipamentoDto equipamentoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("equipamentoDto", equipamentoDto);
            model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEquipamentosDto::new));
            model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoEquipamentoDto::new));
            model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarNotaFiscalDto::new));
            model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
            model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));
            model.addAttribute("filtrarEquipamentoDto", new FiltrarEquipamentoDto());
            return "equipamento/cadastrarEquipamento";
        }

        var resultado = equipamentoSvc.save(equipamentoDto, new EquipamentoModel());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/equipamentos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = equipamentoSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/equipamentos";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid EquipamentoDto equipamentoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("equipamentoDto", equipamentoDto);
            model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoEquipamentoDto::new));
            model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarNotaFiscalDto::new));
            model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
            model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));
            return "equipamento/atualizarEquipamento";
        }

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(id);

        if (equipamentoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.equipamentoNaoEncontrado()
            );

            return "redirect:/equipamentos";
        }

        var resultado = equipamentoSvc.save(equipamentoDto, equipamentoModelOptional.get());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/equipamentos";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("equipamentoDto", new EquipamentoDto());
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarEquipamentosDto::new));
        model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoEquipamentoDto::new));
        model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarNotaFiscalDto::new));
        model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
        model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));
        model.addAttribute("filtrarEquipamentoDto", new FiltrarEquipamentoDto());

        return "equipamento/cadastrarEquipamento";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(id);

        if (equipamentoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/equipamentos";
        }

        model.addAttribute("equipamentoDto", new VisualizarEquipamentoDto(equipamentoModelOptional.get()));
        model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoEquipamentoDto::new));
        model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarNotaFiscalDto::new));
        model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
        model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));

        return "equipamento/atualizarEquipamento";
    }

    @PostMapping("/filtrar")
    public String filter(Pageable pageable, Model model, FiltrarEquipamentoDto filtrarEquipamentoDto) {

        model.addAttribute("listaDeTiposDeEquipamento", tipoEquipamentoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarTipoEquipamentoDto::new));
        model.addAttribute("listaDeNotasFiscais", notaFiscalSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarNotaFiscalDto::new));
        model.addAttribute("listaDeLocalizacoes", localizacaoSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLocalizacaoDto::new));
        model.addAttribute("listaDeContratosDeEquipamentosDeTerceiros", contratoEquipamentoTerceiroSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarContratoEquipamentoTerceiroDto::new));
        model.addAttribute("listaDeEquipamentos", equipamentoSvc.findAllByFilter(pageable, filtrarEquipamentoDto).map(ListarEquipamentosDto::new));
        model.addAttribute("equipamentoDto", new EquipamentoDto());
        model.addAttribute("filtrarEquipamentoDto", new FiltrarEquipamentoDto());

        return "equipamento/cadastrarEquipamento";
    }
}
