package fr.ekwateur.application.domain.billing;

import fr.ekwateur.application.domain.client.Client;
import fr.ekwateur.application.domain.consumption.EnergyConsumed;
import fr.ekwateur.application.port.out.EnergyTariffProvider;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.temporal.Temporal;

@Getter
@Setter
public class Invoice {

    private Client client;
    private EnergyTariffProvider energyTariffProvider;

    public Invoice(Client client, EnergyTariffProvider energyTariffProvider) {
        if (energyTariffProvider == null) {
            throw new IllegalArgumentException("The unit price factory is not initialized");
        }

        this.client = client;
        this.energyTariffProvider = energyTariffProvider;
    }

    public BigDecimal getAmount(Temporal YearMonth) {
        EnergyTariff energyTariff = energyTariffProvider.getEnergyTariff(client);
        EnergyConsumed energyConsumed = client.getEnergyConsumption().getMonthlyEnergyConsumption(YearMonth);

        return energyTariff.electricityPrice().multiply(BigDecimal.valueOf(energyConsumed.electrical()))
                .add(energyTariff.gasPrice().multiply(BigDecimal.valueOf(energyConsumed.gas())));
    }
}
