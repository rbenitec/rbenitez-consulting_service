package service.consulting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class ConsultingTransactionDto {
    private String productId;
    private String productType;
    private List<TransactionDto> transactions;

    public ConsultingTransactionDto(String productId, String productType) {
        this.productId = productId;
        this.productType = productType;
        this.transactions = new ArrayList<>();
    }

}
