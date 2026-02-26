package com.streammaster.api.service;

import com.streammaster.api.repository.ClienteRepository;
import com.streammaster.api.model.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }
}