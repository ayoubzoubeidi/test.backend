package fr.ekwateur.application.port.out;

import fr.ekwateur.application.domain.billing.EnergyTariff;
import fr.ekwateur.application.domain.client.Client;

public interface EnergyTariffProvider {
    EnergyTariff getEnergyTariff(Client client);
}
