package service.consulting.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.consulting.client.AccountClientConnector;
import service.consulting.client.dto.AccountServiceResponseDto;
import service.consulting.rest.HttpInterface;

@Service
@RequiredArgsConstructor
public class AccountClientImpl implements AccountClientConnector {

    private final HttpInterface httpInterface;

    @Value("${spring.config.client.account.url}")
    private String url;

    @Override
    public Mono<AccountServiceResponseDto> getAccountBalanceByProductId(String productId) {
        return httpInterface.getHttpMono(url.concat("/").concat(productId), null);
    }

    @Override
    public Flux<AccountServiceResponseDto> getAccountTransactionsByProductId(String productId) {
        return httpInterface.getHttpFlux(url.concat("/").concat(productId), null);
    }
}
