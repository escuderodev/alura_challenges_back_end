package br.com.escuderodev.api.depoimento.models.destinos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "Destino")
@Table(name = "destino")
@Getter
@Setter
@ToString
public class Destino {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDestino;
    private String urlImagem;
    private String nome;
    private BigDecimal preco;
    @CreationTimestamp
    private Timestamp dataCriacao;
    @UpdateTimestamp
    private Timestamp dataAtualizacao;

    public Destino(DadosCadastroDestino dados) {
        this.urlImagem = dados.urlImagem();
        this.nome = dados.nome().toUpperCase();
        this.preco = dados.preco();
    }

    public Destino() {
    }

    public void atualizaDados(DadosAtualizaDestino dados) {
        this.urlImagem = dados.urlImagem();
        this.nome = dados.nome().toUpperCase();
        this.preco = dados.preco();
    }
}
