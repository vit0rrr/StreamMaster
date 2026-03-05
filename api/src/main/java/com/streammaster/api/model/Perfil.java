package com.streammaster.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table  (name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore

    private Cliente cliente;


    public Perfil(){}

    public Perfil(String nome, Cliente cliente) {
        this.nome = nome;
        this.cliente = cliente;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}


}   

