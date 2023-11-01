package fr.ekwateur.application.domain.client;

import fr.ekwateur.application.domain.consumption.EnergyConsumption;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
@Setter
public abstract class Client {

    protected String clientReference;
    protected ClientType clientType;
    protected EnergyConsumption energyConsumption;

    private static Pattern CLIENT_REFERENCE_REGEX = Pattern.compile("^EKW\\d{8}$");

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
