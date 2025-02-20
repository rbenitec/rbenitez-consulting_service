package service.consulting.client;

import reactor.core.publisher.Flux;
import service.consulting.client.dto.TransactionServiceResponseDto;

public interface TransactionClientConnector {
    Flux<TransactionServiceResponseDto> getTransactionsByProductId(String productId);
}
