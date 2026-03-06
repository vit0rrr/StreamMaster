package com.streammaster.api.service;

import com.streammaster.api.repository.ClienteRepository;
import com.streammaster.api.model.Cliente;
import com.streammaster.api.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.streammaster.api.dto.ClienteDTO;
import java.util.List;

@Service
public class ClienteService {
    
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    // CREAT 
    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        return repository.save(cliente);
    }

    // READ BUSCAR TODOS COM PERFIS
    public Page<Cliente> listarTodos(Pageable paginacao) {
        return repository.findAllComPerfis(paginacao);
    }

    
    //READ   public Cliente buscarPorId(Long id) {
    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }


    //UPDATE
    public Cliente atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        return repository.save(cliente);
    }


    //DELETE 
    public void deletar(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        repository.delete(cliente);
    }


  
    
}

