package br.com.escuderodev.api.depoimento.models.destinos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Long>{
    List<Destino> findDestinoByNome(String nome);
}
