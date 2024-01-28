package br.com.fiap.api.pagamentos.dataprovider.configuration;

import br.com.fiap.api.pagamentos.core.dataprovider.repository.OrderRepository;
import br.com.fiap.api.pagamentos.core.usecase.OrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortConfiguration {

    @Bean
    ProductUseCase productUseCase(ProductRepository productRepositoryPort) {
        return new ProductUseCaseImpl(productRepositoryPort);
    }

    @Bean
    OrderUseCase orderUseCase(
            OrderRepository orderRepositoryPort,
            ProductRepository productRepositoryPort,
            ClientRepository clientRepositoryPort) {
        return new OrderUseCaseImpl(orderRepositoryPort, productRepositoryPort, clientRepositoryPort);
    }

    @Bean
    ClientUseCase clientUseCase(ClientRepository clientRepository) {
        return new ClientUseCaseImpl(clientRepository);
    }



}
