package service.consulting.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionServiceResponseDto {
    private String idTransaction;
    private String idAccount;
    private String idCredit;
    private Double amount;
    private String typeTransaction;
    private String dateTransaction;
}
