package fr.ekwateur.adapters.out;

import fr.ekwateur.application.domain.client.Client;
import fr.ekwateur.application.port.out.GetClientByReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetClientByReferenceInMemoryTest {

    private GetClientByReference getClientByReference;
    
    private String EXISTING_CLIENT_REFERENCE = "EKW58612358";
    private String NOT_EXISTING_CLIENT_REFERENCE = "EKW11111111";

    @BeforeEach
    public void setUp() {
        getClientByReference = GetClientByReferenceInMemory.getInstance();
    }
    
    @Test
    void getClientByReference_ExistingClient() {
        Client client = getClientByReference.getClientByReference(EXISTING_CLIENT_REFERENCE);
        
        assertNotNull(client);
    }

    @Test
    void getClientByReference_NotExistingClient() {
        assertThrows(RuntimeException.class, () -> getClientByReference.getClientByReference(NOT_EXISTING_CLIENT_REFERENCE));
    }
}