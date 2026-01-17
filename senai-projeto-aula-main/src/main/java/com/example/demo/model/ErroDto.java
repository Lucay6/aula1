package com.example.demo.model;

import java.time.LocalDateTime;

public class ErroDto {
    private String timestamp = LocalDateTime.now().toString();
    private int status;
    private String erro;
    private String mensagem;

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ErroDto(String timestamp, int status, String erro, String mensagem) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
    }

    public ErroDto() {
    }
}
