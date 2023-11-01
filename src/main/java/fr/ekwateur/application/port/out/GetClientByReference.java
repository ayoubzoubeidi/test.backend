package fr.ekwateur.application.port.out;


import fr.ekwateur.application.domain.client.Client;

public interface GetClientByReference {
    Client getClientByReference(String clientReference);
}
