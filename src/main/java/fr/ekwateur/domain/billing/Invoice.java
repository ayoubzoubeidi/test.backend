package fr.ekwateur.domain.billing;

import fr.ekwateur.domain.client.Client;
import fr.ekwateur.domain.consumption.EnergyConsumed;
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
