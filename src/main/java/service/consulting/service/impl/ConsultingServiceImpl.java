package service.consulting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.consulting.client.AccountClientConnector;
import service.consulting.client.CreditClientConnector;
import service.consulting.client.TransactionClientConnector;
import service.consulting.dto.ConsultingBalanceDto;
import service.consulting.dto.ConsultingTransactionDto;
import service.consulting.dto.TransactionDto;
import service.consulting.service.ConsultingService;

@Service
@RequiredArgsConstructor
public class ConsultingServiceImpl implements ConsultingService {

    private final CreditClientConnector creditClientConnector;

    private final AccountClientConnector accountClientConnector;

    private final TransactionClientConnector transactionClientConnector;

    /**
     * Consulta de saldos de un producto.
     *
     * @param productId
     * @return
     */
    @Override
    public Mono<ConsultingBalanceDto> consultBalance(String productId) {
        return isCredit(productId)
                .flatMap(isCredit -> { //Puedes convertirlo a map para ver la utilidad del flatMap
                    if (isCredit) {
                        return creditClientConnector.getCreditBalanceByProductId(productId)
                                .map(creditServiceResponseDto -> ConsultingBalanceDto.builder()
                                        .productId(creditServiceResponseDto.getId())
                                        .productType(creditServiceResponseDto.getCreditType())
                                        .amountAvailable(creditServiceResponseDto.getAmount())
                                        .build());
                    } else {
                        return accountClientConnector.getAccountBalanceByProductId(productId)
                                .map(accountServiceResponseDto -> ConsultingBalanceDto.builder()
                                        .productId(accountServiceResponseDto.getId())
                                        .productType(accountServiceResponseDto.getTypeAccount())
                                        .amountAvailable(accountServiceResponseDto.getOpeningBalance())
                                        .build());
                    }
                });
    }

    /**
     * Consulta de movimiento de un producto
     *
     * @param productId
     * @return
     */
    @Override
    public Mono<ConsultingTransactionDto> consultTransactions(String productId) {
        return isCredit(productId)
                .flatMap(isCredit -> {
                    if (isCredit) {
                        return creditClientConnector.getCreditBalanceByProductId(productId)
                                .map(creditServiceResponseDto -> new ConsultingTransactionDto(creditServiceResponseDto.getId(), creditServiceResponseDto.getCreditType()))
                                .flatMap(consultingTransactionDto -> transactionClientConnector.getTransactionsByProductId(productId)
                                        .collectList()
                                        .map(transactionResponseList -> {
                                            transactionResponseList.forEach(transactionResponse -> consultingTransactionDto.getTransactions()
                                                    .add(TransactionDto.builder()
                                                            .transactionId(transactionResponse.getIdTransaction())
                                                            .transactionType(transactionResponse.getTypeTransaction())
                                                            .transactionDate(transactionResponse.getDateTransaction())
                                                            .amount(transactionResponse.getAmount())
                                                            .build()));
                                            return consultingTransactionDto;
                                        }));

                    } else {
                        return accountClientConnector.getAccountBalanceByProductId(productId)
                                .map(accountServiceResponseDto -> new ConsultingTransactionDto(accountServiceResponseDto.getId(), accountServiceResponseDto.getTypeAccount()))
                                .flatMap(consultingTransactionDto -> transactionClientConnector.getTransactionsByProductId(productId)
                                        .collectList()
                                        .map(transactionResponseList -> {
                                            transactionResponseList.forEach(transactionResponse -> consultingTransactionDto.getTransactions()
                                                    .add(TransactionDto.builder()
                                                            .transactionId(transactionResponse.getIdTransaction())
                                                            .transactionType(transactionResponse.getTypeTransaction())
                                                            .transactionDate(transactionResponse.getDateTransaction())
                                                            .amount(transactionResponse.getAmount())
                                                            .build()));
                                            return consultingTransactionDto;
                                        }));
                    }
                });
    }

    public Mono<Boolean> isCredit(String productId) {
        return Mono.just(productId.startsWith("P"));
    }

}
