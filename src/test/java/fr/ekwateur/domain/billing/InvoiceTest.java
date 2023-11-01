package fr.ekwateur.domain.billing;

import fr.ekwateur.domain.client.Civilite;
import fr.ekwateur.domain.client.Client;
import fr.ekwateur.domain.client.IndividualClient;
import fr.ekwateur.domain.consumption.EnergyConsumption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static fr.ekwateur.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class InvoiceTest {

    @Mock
    EnergyConsumption energyConsumption;
    @Mock
    private EnergyTariffProvider energyTariffProvider;
    private Client client;
    private Invoice invoice;

    private final EnergyTariff RETURNED_ENERGY_TARIFF = new EnergyTariff(RETURNED_ELECTRICITY_PRICE, RETURNED_GAS_PRICE);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new IndividualClient(VALID_REFERENCE, Civilite.MONSIUER, PRENOM, NOM, energyConsumption);
        invoice = new Invoice(client, energyTariffProvider);
    }

    @Test
    public void getAmount() {
        when(energyConsumption.getMonthlyEnergyConsumption(FEBRUARY_2023)).thenReturn(RETURNED_ENERGY_CONSUMED);
        when(energyTariffProvider.getEnergyTariff(client)).thenReturn(RETURNED_ENERGY_TARIFF);

        BigDecimal returnedAmount = invoice.getAmount(FEBRUARY_2023);

        assertEquals(0, EXPECTED_AMOUNT.compareTo(returnedAmount));
    }

    @Test void getAmount_WithNullTariffProvider_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Invoice(client, null));
    }
}