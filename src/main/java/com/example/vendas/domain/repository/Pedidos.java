package com.example.vendas.domain.repository;

import com.example.vendas.domain.models.Pedido;
import com.example.vendas.domain.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
