package com.streammaster.api.controler;

import com.streammaster.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.streammaster.api.dto.ClienteDTO;
import com.streammaster.api.model.Cliente;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }


    //CREATE (POST) - ROTA: POST / CLIENTES
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody @Valid ClienteDTO dto) {
        Cliente novoCliente = service.salvar (dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

   
    //READ (GET) - ROTA: GET / CLIENTES
    @GetMapping
    public ResponseEntity<Page<Cliente>> listarClientes(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        
        Page<Cliente> clientesPaginados = service.listarTodos(paginacao);
        return ResponseEntity.ok(clientesPaginados);    
    }

    //READ (GET) por ID - ROTA: GET / CLIENTES/{ID}
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    //UPDATE (PUT) - ROTA: PUT / CLIENTES/{ID}
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDTO dto) {
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