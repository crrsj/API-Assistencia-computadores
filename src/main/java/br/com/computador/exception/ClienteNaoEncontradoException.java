package br.com.computador.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String mensagem) {
    }

    public ClienteNaoEncontradoException(){
        super();
    }
}
