package com.example.vendas.service;


import com.example.vendas.domain.models.Pedido;
import com.example.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido save( PedidoDTO dto );

    Optional<Pedido> obterPedidoCompleto( Integer id);
}
