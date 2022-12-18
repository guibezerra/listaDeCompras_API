package br.com.listaDeComprasApi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="produtos")
public class Produto {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
    private Long idProduto;

    @Column(name="nome")
    private String nome;

    @Column(name="quantidade")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name="id_lista_de_compras")
    private ListaDeCompras listaDeCompras;

}
