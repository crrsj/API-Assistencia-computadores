package br.com.computador.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Data
@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    @Email
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Aparelho> aparelhos;
}
