package br.com.escuderodev.api.depoimento.controller;

import br.com.escuderodev.api.depoimento.models.destinos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/destinos")
@CrossOrigin
public class DestinoController {

    @Autowired
    private DestinoRepository repository;

    @GetMapping
    public ResponseEntity<List<Destino>> listarDestinos() {
        List<Destino> destinos = repository.findAll();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Destino>> listarDestinosPorNome(@PathVariable String nome) {
        List<Destino> destinos = repository.findDestinoByNome(nome.trim().toUpperCase());
        if (destinos.size() == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(destinos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDestino(@RequestBody @Valid DadosCadastroDestino dados, UriComponentsBuilder uriComponentsBuilder) {
        var destino = new Destino(dados);
        repository.save(destino);
        var uri = uriComponentsBuilder.path("depoimento/{idDestino}").buildAndExpand(destino.getIdDestino()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhaDestino(destino));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDestino(@RequestBody @Valid DadosAtualizaDestino dados) {
        var destino = repository.getReferenceById(dados.idDestino());
        destino.atualizaDados(dados);
        return ResponseEntity.ok(new DadosDetalhaDestino(destino));
    }

    @DeleteMapping("/{idDestino}")
    @Transactional
    public ResponseEntity deletarDepoimento(@PathVariable Long idDestino) {
        var destino = repository.getReferenceById(idDestino);
        repository.deleteById(idDestino);
        return ResponseEntity.noContent().build();
    }
}
