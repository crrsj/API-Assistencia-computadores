package br.com.computador.exception;

public class AparelhoNaoEncontradoException extends RuntimeException{
    public AparelhoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AparelhoNaoEncontradoException(){
        super();
    }
}
