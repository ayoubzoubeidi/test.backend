package fr.ekwateur.domain.consumption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static fr.ekwateur.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyConsumptionTest {

    private EnergyConsumption energyConsumption;

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