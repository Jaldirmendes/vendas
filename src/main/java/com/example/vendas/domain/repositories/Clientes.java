package com.example.vendas.domain.repositories;

import com.example.vendas.domain.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(" select c from Cliente c left join fetch c.pedidos p where c.id =:id ")
    Cliente finClienteFetchPedidos( @Param("id") Integer id );

}
