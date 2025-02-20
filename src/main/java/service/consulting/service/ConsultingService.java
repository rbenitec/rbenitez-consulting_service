package service.consulting.service;


import reactor.core.publisher.Mono;
import service.consulting.dto.ConsultingBalanceDto;
import service.consulting.dto.ConsultingTransactionDto;

public interface ConsultingService {

    Mono<ConsultingBalanceDto> consultBalance(String productId);

    Mono<ConsultingTransactionDto> consultTransactions(String productId);
}
