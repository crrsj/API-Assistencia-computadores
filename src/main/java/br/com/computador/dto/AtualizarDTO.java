package br.com.computador.dto;

import br.com.computador.enums.Defeito;
import br.com.computador.enums.Marca;
import br.com.computador.enums.TipoAparelho;
import lombok.Data;

@Data
public class AtualizarDTO {
    private TipoAparelho tipo;
    private Marca marca;
    private String modelo;
    private Defeito defeito;
    private String descricao;
}
