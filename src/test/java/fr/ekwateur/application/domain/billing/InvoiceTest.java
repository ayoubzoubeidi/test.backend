package fr.ekwateur.application.domain.billing;

import fr.ekwateur.application.domain.client.Civilite;
import fr.ekwateur.application.domain.client.Client;
import fr.ekwateur.application.domain.client.IndividualClient;
import fr.ekwateur.application.domain.consumption.EnergyConsumed;
import fr.ekwateur.application.domain.consumption.EnergyConsumption;
import fr.ekwateur.application.port.out.EnergyTariffProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.YearMonth;

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

    public static final String VALID_REFERENCE = "EKW57198516";
    public static final String PRENOM = "Prenom";
    public static final String NOM = "Nom";
    public static final EnergyConsumed RETURNED_ENERGY_CONSUMED = new EnergyConsumed(20.0d, 50.0d);
    public static final BigDecimal RETURNED_ELECTRICITY_PRICE = new BigDecimal("20.0");
    public static final BigDecimal RETURNED_GAS_PRICE = new BigDecimal("30.0");
    public static final BigDecimal EXPECTED_AMOUNT = new BigDecimal("1900.0");
    public static final YearMonth FEBRUARY_2023 = YearMonth.of(2023, 2);
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