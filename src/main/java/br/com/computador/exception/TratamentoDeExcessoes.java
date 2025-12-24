package br.com.computador.exception;

import br.com.computador.dto.CanposValidos;
import br.com.computador.dto.MensagemDeErro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class TratamentoDeExcessoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>validarCampos(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(CanposValidos::new).toList());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<MensagemDeErro>clienteNaoEncontrado(){
       var msg = new MensagemDeErro(HttpStatus.NOT_FOUND,"Cliente não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(AparelhoNaoEncontradoException.class)
    public ResponseEntity<MensagemDeErro>aparelhoNaoEncontrado(){
        var msg = new MensagemDeErro(HttpStatus.NOT_FOUND,"Aparelho não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

}
