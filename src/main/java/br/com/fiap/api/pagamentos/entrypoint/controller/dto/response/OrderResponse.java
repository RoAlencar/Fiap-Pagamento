package br.com.fiap.api.pagamentos.entrypoint.controller.dto.response;

import br.com.fiap.api.pagamentos.core.Order;
import br.com.fiap.api.pagamentos.dataprovider.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record OrderResponse(UUID orderId, Boolean isPaymentReceived, OrderStatusEnum orderStatus,
                            List<Product> orderProducts, Double orderPrice, ClientEntity client) {

    public static OrderResponse fromEntityToResponse(Order order) {
        return new OrderResponse(order.getOrderId(), order.getPaymentReceived(), order.getOrderStatus(),
                order.getOrderProducts(), order.getOrderPrice(), order.getClient() != null ? ClientDto.fromClientToClientEntity(order.getClient()) : null);
    }
}
