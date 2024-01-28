package br.com.fiap.api.pagamentos.core.usecase.impl;

import br.com.fiap.api.pagamentos.core.Order;
import br.com.fiap.api.pagamentos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pagamentos.core.usecase.OrderUseCase;
import br.com.fiap.api.pagamentos.dataprovider.enumeration.OrderStatusEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository orderRepository;

    public OrderUseCaseImpl(
            OrderRepository orderRepository,
            ProductRepository productRepositoryPort,
            ClientRepository clientRepositoryPort) {
        this.orderRepository = orderRepository;
        this.productRepositoryPort = productRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
    }


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return Optional.ofNullable(orderRepository.getById(id));
    }

    @Override
    public Order saveOrder(Order order) {
        List<Product> products = productRepositoryPort.getAllById(order.getOrderProductIds());
        Optional<Client> client = clientRepositoryPort.identifyClientByCpf(order.getClient().getClientCpf());

        order.setOrderPrice(calculateOrderPrice(products));
        order.setOrderProducts(products);
        order.setClient(client.get());

        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(OrderStatusEnum status, UUID id) {
        orderRepository.updateByOrderStatusAndOrderId(status, id);

    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.delete(id);
    }

    @Override
    public List<Order> orderCheckout(String cpf) {
        return orderRepository.getAllByClientCpf(cpf);
    }

    public Double calculateOrderPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum();
    }
}
