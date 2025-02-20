package service.consulting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultingBalanceDto {

    @Id
    private String productId;
    private String productType;
    private Double amountAvailable;
}
