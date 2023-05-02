package com.controleestoquensgio.util;

public class Resultado {

    private String mensagem;
    private String erroOuSucesso;

    public Resultado (String erroOuSucesso, String mensagem) {
        this.mensagem = mensagem;
        this.erroOuSucesso = erroOuSucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getErroOuSucesso() {
        return erroOuSucesso;
    }

    public void setErroOuSucesso(String erroOuSucesso) {
        this.erroOuSucesso = erroOuSucesso;
    }

}
