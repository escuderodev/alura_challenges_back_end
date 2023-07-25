package br.com.escuderodev.api.depoimento.models.destinos;

import java.math.BigDecimal;

public record DadosDetalhaDestino(Long idDestino, String urlImagem, String nome, BigDecimal preco) {

    public DadosDetalhaDestino(Destino destino) {
        this(destino.getIdDestino(), destino.getUrlImagem(), destino.getNome(), destino.getPreco());
    }
}
