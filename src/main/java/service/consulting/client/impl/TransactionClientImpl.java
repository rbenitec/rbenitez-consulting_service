package service.consulting.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import service.consulting.client.TransactionClientConnector;
import service.consulting.client.dto.TransactionServiceResponseDto;
import service.consulting.rest.HttpInterface;

@Service
@RequiredArgsConstructor
public class TransactionClientImpl implements TransactionClientConnector {

    private final HttpInterface httpInterface;

    @Value("${spring.config.client.transaction.url}")
    private String url;

    @Override
    public Flux<TransactionServiceResponseDto> getTransactionsByProductId(String productId) {
        return httpInterface.getHttpFlux(url.concat("/").concat(productId), null);
    }
}
