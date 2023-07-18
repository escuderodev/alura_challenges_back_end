package br.com.escuderodev.api.depoimento.models.depoimento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity(name = "Depoimenmto")
@Table(name = "depoimento")
@Getter
@Setter
@ToString
public class Depoimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepoimento;
    private String urlImagem;
    private String depoimento;
    private String nomeDoAutor;
    @CreationTimestamp
    private Timestamp dataDeCriacao;
    @UpdateTimestamp
    private Timestamp dataDeAtualizacao;

    public Depoimento(DadosCadastroDepoimento dados) {
        this.urlImagem = dados.urlImagem();
        this.depoimento = dados.depoimento();
        this.nomeDoAutor = dados.nomeDoAutor();
    }

    public Depoimento() {
    }

    public void atualizaDados(DadosAtualizaDepoimento dados) {
        this.idDepoimento = dados.idDepoimento();
        this.urlImagem = dados.urlImagem();
        this.depoimento = dados.depoimento();
        this.nomeDoAutor = dados.nomeDoAutor();

    }
}
