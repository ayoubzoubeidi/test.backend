package fr.ekwateur.domain.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProClient extends Client {

    private String siretNumber;
    private String raisonSocial;
    private BigDecimal turnover;

    protected ProClient(String clientReference, String raisonSocial, String siretNumber, BigDecimal turnover) {
        super(clientReference, ClientType.PRO);
        this.siretNumber = siretNumber;
        this.raisonSocial = raisonSocial;
        this.turnover = turnover;
    }
}
