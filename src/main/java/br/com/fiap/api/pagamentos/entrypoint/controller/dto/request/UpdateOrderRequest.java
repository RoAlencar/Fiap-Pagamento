package br.com.fiap.api.pagamentos.entrypoint.controller.dto.request;

import br.com.fiap.api.pagamentos.dataprovider.enumeration.OrderStatusEnum;

import java.util.UUID;

public record UpdateOrderRequest(UUID orderId, OrderStatusEnum orderStatus) {
}
