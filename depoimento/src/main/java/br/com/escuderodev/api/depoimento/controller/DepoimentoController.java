package br.com.escuderodev.api.depoimento.controller;

import br.com.escuderodev.api.depoimento.models.depoimento.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/depoimentos")
@CrossOrigin
public class DepoimentoController {

    @Autowired
    private DepoimentoRepository repository;

    @GetMapping
    public ResponseEntity<List<Depoimento>> listarDepoimentos() {
        List<Depoimento> depoimentos = repository.findAll();
        return ResponseEntity.ok(depoimentos);
    }

    @GetMapping("/depoimentos-home")
    public ResponseEntity<Page<DadosDetalhaDepoimento>> listar3Depoimentos(@PageableDefault(size = 3)Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhaDepoimento::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDepoimento(@RequestBody @Valid DadosCadastroDepoimento dados, UriComponentsBuilder uriComponentsBuilder) {
        var depoimento = new Depoimento(dados);
        repository.save(depoimento);
        var uri = uriComponentsBuilder.path("depoimento/{idDepoimento}").buildAndExpand(depoimento.getIdDepoimento()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhaDepoimento(depoimento));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDepoimento(@RequestBody @Valid DadosAtualizaDepoimento dados) {
        var depoimento = repository.getReferenceById(dados.idDepoimento());
        depoimento.atualizaDados(dados);
        return ResponseEntity.ok(new DadosDetalhaDepoimento(depoimento));
    }

    @DeleteMapping("/{idDepoimento}")
    @Transactional
    public ResponseEntity deletarDepoimento(@PathVariable Long idDepoimento) {
        var depoimento = repository.getReferenceById(idDepoimento);
        repository.deleteById(idDepoimento);
        return ResponseEntity.noContent().build();
    }
}
