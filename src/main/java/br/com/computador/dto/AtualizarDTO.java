package br.com.computador.dto;

import br.com.computador.enums.Defeito;
import br.com.computador.enums.Marca;
import br.com.computador.enums.TipoAparelho;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AtualizarDTO {
    private TipoAparelho tipo;
    private Marca marca;
    @NotBlank(message = "não pode estar em branco.")
    private String modelo;
    private Defeito defeito;
    private String descricao;
}
