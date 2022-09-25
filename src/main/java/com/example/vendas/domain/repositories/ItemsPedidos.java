package com.example.vendas.domain.repositories;

import com.example.vendas.domain.models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {
}
