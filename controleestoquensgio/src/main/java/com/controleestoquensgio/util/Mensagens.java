package com.controleestoquensgio.util;

public class Mensagens {
    public static String operacaoBemSucedida() { return "Operação bem-sucedida"; }
    public static String operacaoBemSucedidaTipoDeMensagem() { return ErroOuSucesso.SUCESSO.name(); }
    public static String tipoDeEquipamentoNaoEncontrado() { return "Tipo de equipamento não encontrado!"; }
    public static String tipoDeEquipamentoNaoEncontradoTipoDeMensagem() { return ErroOuSucesso.ERRO.name(); }
    public static String tipoDeAcessoNaoEncontrado() { return ErroOuSucesso.ERRO.name(); }
    public static String tipoDeAcessoNaoEncontradoTipoDeMensagem() { return ErroOuSucesso.ERRO.name(); }
    public static String tipoDeColaboradorNaoEncontrado() { return "Tipo de colaborador não encontrado!"; }
    public static String tipoDeColaboradorNaoEncontradoTipoDeMensagem() { return ErroOuSucesso.ERRO.name(); }
}
