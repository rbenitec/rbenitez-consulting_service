package service.consulting.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.consulting.client.dto.CreditServiceResponseDto;

public interface CreditClientConnector {
    Mono<CreditServiceResponseDto> getCreditBalanceByProductId(String productId);
    Flux<CreditServiceResponseDto> getCreditTransactionsByProductId(String productId);
}
