package service.consulting.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.consulting.dto.ConsultingBalanceDto;
import service.consulting.dto.ConsultingTransactionDto;
import service.consulting.service.ConsultingService;

@RestController
@RequestMapping("/consulting")
@RequiredArgsConstructor
public class CustomerController {

    private final ConsultingService consultingService;

    //uri: "mongodb+srv://rbenitec:rjbc2095@cluster0.zaixl.mongodb.net/MongoPractica?retryWrites=true&w=majority"

    @GetMapping("/balance/{productId}")   // Consultar saldos de un producto
    public Mono<ResponseEntity<ConsultingBalanceDto>> consultBalance(@PathVariable("productId") String productId) {
        return consultingService.consultBalance(productId)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/transactions/{productId}")   // Consultar Movimientos de un producto
    public Mono<ResponseEntity<ConsultingTransactionDto>> consultTransactions(@PathVariable("productId") String productId) {
        return consultingService.consultTransactions(productId)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
