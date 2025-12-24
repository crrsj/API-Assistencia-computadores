package br.com.computador.dto;

import br.com.computador.entity.Aparelho;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    private Long id;
    @NotBlank(message = "não pode estar em branco.")
    private String nome;
    @NotBlank(message = "não pode estar em branco.")
    private String telefone;
    @NotBlank(message = "não pode estar em branco.")
    private String email;
    private List<Aparelho> aparelhos;
}
