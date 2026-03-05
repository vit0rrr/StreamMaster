package com.streammaster.api.repository;

import com.streammaster.api.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.perfis WHERE c.id = :id")
    List<Cliente> findAllComPerfis();
}