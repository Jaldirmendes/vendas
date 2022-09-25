package com.example.vendas.service.impl;

import com.example.vendas.domain.models.Cliente;
import com.example.vendas.domain.models.ItemPedido;
import com.example.vendas.domain.models.Pedido;
import com.example.vendas.domain.models.Produto;
import com.example.vendas.domain.repositories.Clientes;
import com.example.vendas.domain.repositories.ItemsPedidos;
import com.example.vendas.domain.repositories.Pedidos;
import com.example.vendas.domain.repositories.Produtos;
import com.example.vendas.exception.RegraNegocioException;
import com.example.vendas.rest.dto.ItemPedidoDTO;
import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepostiroty;
    private final ItemsPedidos itemsPedidosRepository;

    @Override
    @Transactional
    public Pedido save( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(() ->
                        new RegraNegocioException("Código de Cliente Inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = convertItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidosRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> convertItems( Pedido pedido, List<ItemPedidoDTO> items){
        if (items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items.stream().map( dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtosRepostiroty.findById(idProduto)
                    .orElseThrow(
                            () -> new RegraNegocioException("Código de Produto inválido: "+ idProduto));

             ItemPedido itemPedido = new ItemPedido();
             itemPedido.setQuantidade(dto.getQuantidade());
             itemPedido.setPedido(pedido);
             itemPedido.setProduto(produto);
             return itemPedido;
        }).collect(Collectors.toList());
    }
}
