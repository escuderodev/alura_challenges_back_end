package br.com.escuderodev.api.depoimento.models.destinos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizaDestino(
        Long idDestino,
        @NotBlank(message = "Url da imagem é obrigatória!")
        String urlImagem,
        @NotBlank(message = "Nome do Destino é obrigatório!")
        String nome,
        @NotNull(message = "Preço do Destino é obrigatório!")
        BigDecimal preco) {
}
