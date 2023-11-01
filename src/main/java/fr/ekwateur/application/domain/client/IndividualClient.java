package fr.ekwateur.application.domain.client;

import fr.ekwateur.application.domain.consumption.EnergyConsumption;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualClient extends Client {

    private Civilite civilite;
    private String firstName;
    private String lastName;

    public IndividualClient(String clientReference, Civilite civilite, String firstName, String lastName) {
        super(clientReference, ClientType.INDIVIDUAL);
        this.civilite = civilite;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public IndividualClient(String clientReference, Civilite civilite, String firstName, String lastName, EnergyConsumption energyConsumption) {
        super(clientReference, ClientType.INDIVIDUAL, energyConsumption);
        this.civilite = civilite;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
