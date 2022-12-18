package br.com.listaDeComprasApi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="lista_de_compras")
public class ListaDeCompras {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_lista_de_compras")
    private Long idListaDeCompras;

    @Column(name="nome")
    private String nome;

    @Column(name="concluida")
    private boolean concluida;

}
