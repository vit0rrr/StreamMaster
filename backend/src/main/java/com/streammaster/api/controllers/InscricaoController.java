package com.streammaster.api.controllers;

import com.streammaster.api.models.entities.Inscricao;
import com.streammaster.api.services.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin(origins = "*") // Libera o Frontend para acessar
public class InscricaoController {

    private final InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }

    // CREATE -> POST (Retorna 201)
    @PostMapping
    public ResponseEntity<Inscricao> criar(@RequestBody Inscricao inscricao) {
        Inscricao novaInscricao = service.salvar(inscricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaInscricao);
    }

    // READ -> GET (Retorna 200)
    @GetMapping
    public ResponseEntity<List<Inscricao>> listarTodas() {
        return ResponseEntity.ok(service.buscarTodas());
    }

    // READ -> GET com ID na URL (Retorna 200)
    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscarUma(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // UPDATE -> PUT com ID na URL (Retorna 200)
    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> atualizar(@PathVariable Long id, @RequestBody Inscricao inscricao) {
        return ResponseEntity.ok(service.atualizar(id, inscricao));
    }

    // DELETE -> DELETE com ID na URL (Retorna 204 Sem Conteúdo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}