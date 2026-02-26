package com.streammaster.api.controller;

import com.streammaster.api.service.ClienteService;
import com.streammaster.api.model.Cliente;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }


    //CREATE (POST) - ROTA: POST / CLIENTES
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente dto) {
        Cliente novoCliente = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }


    //READ (GET) - ROTA: GET / CLIENTES
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    //READ (GET) por ID - ROTA: GET / CLIENTES/{ID}
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    //UPDATE (PUT) - ROTA: PUT / CLIENTES/{ID}
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente dto) {
        Cliente clienteAtualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }
    //DELETE (DELETE) - ROTA: DELETE / CLIENTES/{ID}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}