package br.com.fiap.api.pagamentos.entrypoint.controller.dto.request;

import br.com.fiap.api.pagamentos.core.Order;
import br.com.fiap.api.pagamentos.dataprovider.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(List<UUID> orderProductIds,Client client) {

    public static Order fromResponseToOrder(CreateOrderRequest response) {
        return new Order(UUID.randomUUID(), false, OrderStatusEnum.RECEIVED, null, response.orderProductIds(), null, response.client);
    }
}
