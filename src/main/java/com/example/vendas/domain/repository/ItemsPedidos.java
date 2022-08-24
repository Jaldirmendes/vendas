package com.example.vendas.domain.repository;

import com.example.vendas.domain.models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {
}
