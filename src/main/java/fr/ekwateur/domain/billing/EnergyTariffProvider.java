package fr.ekwateur.domain.billing;

import fr.ekwateur.domain.client.Client;

public interface EnergyTariffProvider {
    EnergyTariff getEnergyTariff(Client client);
}
