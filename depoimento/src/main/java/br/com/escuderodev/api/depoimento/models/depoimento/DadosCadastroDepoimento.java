package br.com.escuderodev.api.depoimento.models.depoimento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDepoimento(
        @NotBlank(message = "url da imagem é obrigatória")
        String urlImagem,
        @NotBlank(message = "depoimento é obrigatório")
        String depoimento,
        @NotBlank(message = "nome do autor é obrigatório")
        String nomeDoAutor) {

}
