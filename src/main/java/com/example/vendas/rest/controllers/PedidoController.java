package com.example.vendas.rest.controllers;

import com.example.vendas.domain.models.ItemPedido;
import com.example.vendas.domain.models.Pedido;
import com.example.vendas.rest.dto.InformacaoItemPedidoDTO;
import com.example.vendas.rest.dto.InformacoesPedidoDTO;
import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

   private PedidoService pedidosService;

    public PedidoController(PedidoService pedidosService) {

        this.pedidosService = pedidosService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = pedidosService.save( dto );
        return pedido.getId();
    }


    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById( @PathVariable Integer id ){
        return pedidosService.obterPedidoCompleto(id)
                .map( p -> converter(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }


    private InformacoesPedidoDTO converter( Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getName())
                .total(pedido.getTotal())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map( item ->
                InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}
