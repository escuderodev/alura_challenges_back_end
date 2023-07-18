package br.com.escuderodev.api.depoimento.models.depoimento;

public record DadosDetalhaDepoimento(Long idDepoimento, String urlImagem, String depoimento, String nomeDoAutor) {
    public DadosDetalhaDepoimento(Depoimento depoimento) {
        this(depoimento.getIdDepoimento(), depoimento.getUrlImagem(), depoimento.getDepoimento(), depoimento.getNomeDoAutor());
    }
}
