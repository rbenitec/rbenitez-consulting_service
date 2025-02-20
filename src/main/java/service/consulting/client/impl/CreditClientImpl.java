package service.consulting.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.consulting.client.CreditClientConnector;
import service.consulting.client.dto.CreditServiceResponseDto;
import service.consulting.rest.HttpInterface;

@Service
@RequiredArgsConstructor
public class CreditClientImpl implements CreditClientConnector {

    private final HttpInterface httpInterface;

    @Value("${spring.config.client.credit.url}")
    private String url;

    @Override
    public Mono<CreditServiceResponseDto> getCreditBalanceByProductId(String productId) {
        return httpInterface.getHttpMono(url.concat("/").concat(productId), null);
    }

    @Override
    public Flux<CreditServiceResponseDto> getCreditTransactionsByProductId(String productId) {
        return httpInterface.getHttpFlux(url.concat("/").concat(productId), null);
    }
}
