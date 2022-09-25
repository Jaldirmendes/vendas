package com.example.vendas.domain.repositories;

import com.example.vendas.domain.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
