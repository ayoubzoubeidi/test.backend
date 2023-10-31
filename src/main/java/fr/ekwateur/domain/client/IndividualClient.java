package fr.ekwateur.domain.client;

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
}
