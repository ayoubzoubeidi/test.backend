package fr.ekwateur.domain.client;

import org.junit.jupiter.api.Test;

import static fr.ekwateur.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    public void testCreateIndividualClient_WithValidInput_ShouldSucceed() {
        Client client = new IndividualClient(VALID_REFERENCE, Civilite.MONSIUER, PRENOM, NOM);
        assertNotNull(client);
        assertEquals(ClientType.INDIVIDUAL, client.getClientType());
    }

    @Test
    public void testCreateProClient_WithValidInput_ShouldSucceed() {
        Client client = new ProClient(VALID_REFERENCE, RAISON_SOCIALE, SIRET, TURNOVER);
        assertNotNull(client);
        assertEquals(ClientType.PRO, client.getClientType());
    }

    @Test
    public void testCreateIndividualClient_WithInvalidReference_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new IndividualClient(INVALID_REFERENCE, Civilite.MONSIUER, PRENOM, NOM));
    }

    @Test
    public void testCreatePro_WithInvalidReference_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new ProClient(INVALID_REFERENCE, RAISON_SOCIALE, SIRET, TURNOVER));
    }
}