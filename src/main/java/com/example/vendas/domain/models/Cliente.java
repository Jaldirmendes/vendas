package com.example.vendas.domain.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 100, updatable = true)
    private String name;

    @Column(name = "cpf", length = 11)
    private String cpf;


    @JsonIgnore
    @OneToMany( mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;


    public Cliente(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
