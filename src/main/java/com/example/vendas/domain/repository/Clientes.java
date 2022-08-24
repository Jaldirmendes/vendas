package com.example.vendas.domain.repository;

import com.example.vendas.domain.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {


    @Query(value = " select c from Cliente c where c.name like :name ")
    List<Cliente> encontrarPorNome( @Param("name") String name );

    boolean existsByName (String name);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id =:id ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );


}
