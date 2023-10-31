package fr.ekwateur.domain.consumption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyConsumptionTest {

    private EnergyConsumption energyConsumption;
    private static final YearMonth FEBRUARY_2023 = YearMonth.of(2023, 2);
    private static final double FEBRUARY_2023_ELECTRICITY_CONSUMPTION = 100.0d;
    private static final double FEBRUARY_2023_GAS_CONSUMPTION = 50.0d;
    private static final YearMonth MARCH_2023_ONLY_ELECTRICITY = YearMonth.of(2023, 3);
    private static final double MARCH_2023_ELECTRICITY_CONSUMPTION = 80.0d;
    private static final YearMonth APRIL_2023_ONLY_GAS = YearMonth.of(2023, 4);
    private static final double APRIL_2023_GAS_CONSUMPTION = 40.0d;
    private static final YearMonth NON_EXISTENT = YearMonth.of(2023, 6);

    @BeforeEach
    public void setUp() {
        energyConsumption = new EnergyConsumption();

        List<MonthlyConsumption> monthlyConsumptions = new ArrayList<>();
        monthlyConsumptions.add(new MonthlyConsumption(EnergyType.ELECTRICITY, FEBRUARY_2023, FEBRUARY_2023_ELECTRICITY_CONSUMPTION));
        monthlyConsumptions.add(new MonthlyConsumption(EnergyType.GAS, FEBRUARY_2023, FEBRUARY_2023_GAS_CONSUMPTION));
        monthlyConsumptions.add(new MonthlyConsumption(EnergyType.ELECTRICITY, MARCH_2023_ONLY_ELECTRICITY, MARCH_2023_ELECTRICITY_CONSUMPTION));
        monthlyConsumptions.add(new MonthlyConsumption(EnergyType.GAS, APRIL_2023_ONLY_GAS, APRIL_2023_GAS_CONSUMPTION));

        energyConsumption.setMonthlyEnergyConsumptions(monthlyConsumptions);
    }

    @Test
    public void testGetMonthlyEnergyConsumption_ExistentYearMonth() {
        EnergyConsumed result = energyConsumption.getMonthlyEnergyConsumption(FEBRUARY_2023);

        assertEquals(FEBRUARY_2023_ELECTRICITY_CONSUMPTION, result.electrical(), 0.01);
        assertEquals(FEBRUARY_2023_GAS_CONSUMPTION, result.gas(), 0.01);
    }

    @Test
    public void testGetMonthlyEnergyConsumption_NonExistentYearMonth() {
        EnergyConsumed result = energyConsumption.getMonthlyEnergyConsumption(NON_EXISTENT);

        assertEquals(0d, result.electrical());
        assertEquals(0d, result.gas());
    }

    @Test
    public void testGetMonthlyEnergyConsumption_ExistentYearMonthOnlyElectricity() {
        EnergyConsumed result = energyConsumption.getMonthlyEnergyConsumption(MARCH_2023_ONLY_ELECTRICITY);

        assertEquals(MARCH_2023_ELECTRICITY_CONSUMPTION, result.electrical());
        assertEquals(0d, result.gas());
    }

    @Test
    public void testGetMonthlyEnergyConsumption_ExistentYearMonthOnlyGas() {
        EnergyConsumed result = energyConsumption.getMonthlyEnergyConsumption(APRIL_2023_ONLY_GAS);

        assertEquals(0d, result.electrical());
        assertEquals(APRIL_2023_GAS_CONSUMPTION, result.gas());
    }
}