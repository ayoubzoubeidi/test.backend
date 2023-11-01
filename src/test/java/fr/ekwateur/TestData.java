package fr.ekwateur;

import fr.ekwateur.domain.consumption.EnergyConsumed;

import java.math.BigDecimal;
import java.time.YearMonth;

public class TestData {

    public static final String VALID_REFERENCE = "EKW57198516";
    public static final String INVALID_REFERENCE = "E198516";
    public static final String PRENOM = "Prenom";
    public static final String NOM = "Nom";
    public static final String SIRET = "2516516165";
    public static final String RAISON_SOCIALE = "test raison";
    public static final BigDecimal TURNOVER = new BigDecimal("25.20");
    public static final EnergyConsumed RETURNED_ENERGY_CONSUMED = new EnergyConsumed(20.0d, 50.0d);
    public static final BigDecimal RETURNED_ELECTRICITY_PRICE = new BigDecimal("20.0");
    public static final BigDecimal RETURNED_GAS_PRICE = new BigDecimal("30.0");
    public static final BigDecimal EXPECTED_AMOUNT = new BigDecimal("1900.0");
    public static final YearMonth FEBRUARY_2023 = YearMonth.of(2023, 2);
    public static final double FEBRUARY_2023_ELECTRICITY_CONSUMPTION = 100.0d;
    public static final double FEBRUARY_2023_GAS_CONSUMPTION = 50.0d;
    public static final YearMonth MARCH_2023_ONLY_ELECTRICITY = YearMonth.of(2023, 3);
    public static final double MARCH_2023_ELECTRICITY_CONSUMPTION = 80.0d;
    public static final YearMonth APRIL_2023_ONLY_GAS = YearMonth.of(2023, 4);
    public static final double APRIL_2023_GAS_CONSUMPTION = 40.0d;
    public static final YearMonth NON_EXISTENT = YearMonth.of(2023, 6);
}
