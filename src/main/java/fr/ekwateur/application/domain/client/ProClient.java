package fr.ekwateur.application.domain.client;

import fr.ekwateur.application.domain.consumption.EnergyConsumption;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProClient extends Client {

    private String siretNumber;
    private String raisonSocial;
    private BigDecimal turnover;

    public ProClient(String clientReference, String raisonSocial, String siretNumber, BigDecimal turnover) {
        super(clientReference, ClientType.PRO);
        this.siretNumber = siretNumber;
        this.raisonSocial = raisonSocial;
        this.turnover = turnover;
    }

    public ProClient(String clientReference, String raisonSocial, String siretNumber, BigDecimal turnover, EnergyConsumption energyConsumption) {
        super(clientReference, ClientType.PRO, energyConsumption);
        this.siretNumber = siretNumber;
        this.raisonSocial = raisonSocial;
        this.turnover = turnover;
    }
}
