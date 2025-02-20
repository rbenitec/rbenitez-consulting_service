package service.consulting.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.consulting.client.dto.AccountServiceResponseDto;

public interface AccountClientConnector {
    Mono<AccountServiceResponseDto> getAccountBalanceByProductId (String productId);
    Flux<AccountServiceResponseDto> getAccountTransactionsByProductId (String productId);
}
