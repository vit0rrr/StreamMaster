package com.streammaster.api.repository;

import com.streammaster.api.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Adicionamos o Pageable aqui e mudamos de List para Page
    @Query(
        value = "SELECT c FROM Cliente c LEFT JOIN FETCH c.perfis",
        countQuery = "SELECT COUNT(c) FROM Cliente c"
    )
    Page<Cliente> findAllComPerfis(Pageable pageable);
}