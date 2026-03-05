package com.streammaster.api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Perfil> perfis = new ArrayList<>();


    // Construtores, Getters e Setters
    public Cliente() {}

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Perfil> getPerfis() { return perfis; }
    public void setPerfis(List<Perfil> perfis) { this.perfis = perfis; }
}