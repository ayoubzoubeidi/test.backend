package fr.ekwateur.domain.client;

import fr.ekwateur.domain.consumption.EnergyConsumption;
import lombok.Getter;
import lombok.Setter;

import static fr.ekwateur.domain.Common.CLIENT_REFERENCE_REGEX;

@Getter
@Setter
public abstract class Client {

    protected String clientReference;
    protected ClientType clientType;
    protected EnergyConsumption energyConsumption;

    protected Client(String clientReference, ClientType clientType, EnergyConsumption energyConsumption) {
        if (!CLIENT_REFERENCE_REGEX.matcher(clientReference).matches()) {
            throw new IllegalArgumentException("Bad Client Reference");
        }
        this.clientReference = clientReference;
        this.clientType = clientType;
        this.energyConsumption = energyConsumption;
    }

    public Client(String clientReference, ClientType clientType) {
        this(clientReference, clientType, new EnergyConsumption());
    }
}
