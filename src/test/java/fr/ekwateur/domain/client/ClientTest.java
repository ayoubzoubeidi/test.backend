package fr.ekwateur.domain.client;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private static final String VALID_REFERENCE = "EKW57198516";
    private static final String INVALID_REFERENCE = "E198516";
    private static final String PRENOM = "Prenom";
    private static final String NOM = "Nom";
    private static final String SIRET = "2516516165";
    private static final String RAISON_SOCIALE = "test raison";
    private static final BigDecimal TURNOVER = new BigDecimal("25.20");

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