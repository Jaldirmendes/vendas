package com.example.vendas.domain.repositories;

import com.example.vendas.domain.models.Pedido;
import com.example.vendas.domain.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    Optional<Pedido> findByIdFetchItens(Integer id);
}
