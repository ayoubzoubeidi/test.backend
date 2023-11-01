package fr.ekwateur.application.domain.client;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    public static final String VALID_REFERENCE = "EKW57198516";
    public static final String INVALID_REFERENCE = "E198516";
    public static final String PRENOM = "Prenom";
    public static final String NOM = "Nom";
    public static final String SIRET = "2516516165";
    public static final String RAISON_SOCIALE = "test raison";
    public static final BigDecimal TURNOVER = new BigDecimal("25.20");

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