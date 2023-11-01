package fr.ekwateur.adapters.out;

import fr.ekwateur.application.domain.billing.EnergyTariff;
import fr.ekwateur.application.domain.client.Civilite;
import fr.ekwateur.application.domain.client.Client;
import fr.ekwateur.application.domain.client.IndividualClient;
import fr.ekwateur.application.domain.client.ProClient;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static fr.ekwateur.adapters.out.DefaultEnergyTariffProvider.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultEnergyTariffProviderTest {

    private DefaultEnergyTariffProvider defaultEnergyTariffProvider = new DefaultEnergyTariffProvider();
    private final EnergyTariff EXPECTED_INDIVIDUAL_TARIFF = new EnergyTariff(new BigDecimal(PARTICULAR_UNIT_ELECTRICITY_PRICE),
            new BigDecimal(PARTICULAR_UNIT_GAS_PRICE));
    private final EnergyTariff EXPECTED_PRO_BIG_TURNOVER_TARIFF = new EnergyTariff(new BigDecimal(PRO_BIG_UNIT_ELECTRICITY_PRICE),
            new BigDecimal(PRO_BIG_UNIT_GAS_PRICE));
    private final EnergyTariff EXPECTED_PRO_SMALL_TURNOVER_TARIFF = new EnergyTariff(new BigDecimal(PRO_SMALL_UNIT_ELECTRICITY_PRICE),
            new BigDecimal(PRO_SMALL_UNIT_GAS_PRICE));
    public static final BigDecimal BIG_TURNOVER = new BigDecimal("10000000");
    public static final BigDecimal SMALL_TURNOVER = new BigDecimal("10000");
    public static final String PRENOM = "Prenom";
    public static final String NOM = "Nom";
    public static final String SIRET = "2516516165";
    public static final String RAISON_SOCIALE = "test raison";
    public static final String VALID_REFERENCE = "EKW57198516";

    @Test
    public void testGetEnergyTariff_IndividualClient() {
        Client individualClient = new IndividualClient(VALID_REFERENCE, Civilite.MONSIUER, PRENOM, NOM);

        EnergyTariff returnedEnergyTariff = defaultEnergyTariffProvider.getEnergyTariff(individualClient);

        BigDecimal returnedElectricityTariff = returnedEnergyTariff.electricityPrice();
        BigDecimal returnedGasTariff = returnedEnergyTariff.gasPrice();

        assertNotNull(returnedElectricityTariff);
        assertEquals(0, EXPECTED_INDIVIDUAL_TARIFF.electricityPrice().compareTo(returnedElectricityTariff));
        assertEquals(0, EXPECTED_INDIVIDUAL_TARIFF.gasPrice().compareTo(returnedGasTariff));
    }

    @Test
    public void testGetEnergyTariff_BigTurnoverProClient() {
        Client proBigTurnoverClient = new ProClient(VALID_REFERENCE, RAISON_SOCIALE, SIRET, BIG_TURNOVER);
        EnergyTariff returnedEnergyTariff = defaultEnergyTariffProvider.getEnergyTariff(proBigTurnoverClient);

        BigDecimal returnedElectricityTariff = returnedEnergyTariff.electricityPrice();
        BigDecimal returnedGasTariff = returnedEnergyTariff.gasPrice();

        assertNotNull(returnedElectricityTariff);
        assertEquals(0, EXPECTED_PRO_BIG_TURNOVER_TARIFF.electricityPrice().compareTo(returnedElectricityTariff));
        assertEquals(0, EXPECTED_PRO_BIG_TURNOVER_TARIFF.gasPrice().compareTo(returnedGasTariff));
    }

    @Test
    public void testGetEnergyTariff_SmallTurnoverProClient() {
        Client proSmallTurnoverClient = new ProClient(VALID_REFERENCE, RAISON_SOCIALE, SIRET, SMALL_TURNOVER);
        EnergyTariff returnedEnergyTariff = defaultEnergyTariffProvider.getEnergyTariff(proSmallTurnoverClient);

        BigDecimal returnedElectricityTariff = returnedEnergyTariff.electricityPrice();
        BigDecimal returnedGasTariff = returnedEnergyTariff.gasPrice();

        assertNotNull(returnedElectricityTariff);
        assertEquals(0, EXPECTED_PRO_SMALL_TURNOVER_TARIFF.electricityPrice().compareTo(returnedElectricityTariff));
        assertEquals(0, EXPECTED_PRO_SMALL_TURNOVER_TARIFF.gasPrice().compareTo(returnedGasTariff));
    }
}