package fr.ekwateur.application.domain.consumption;

import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;
import java.time.temporal.Temporal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class EnergyConsumption {

    private List<MonthlyConsumption> monthlyEnergyConsumptions = new LinkedList<>();

    public EnergyConsumed getMonthlyEnergyConsumption(Temporal temporal) {
        Map<EnergyType, Double> energyTypeToConsumptionMap = monthlyEnergyConsumptions.stream()
                .filter(monthlyConsumption -> monthlyConsumption.getYearMonth().equals(YearMonth.from(temporal)))
                .collect(Collectors.groupingBy(MonthlyConsumption::getEnergyType,
                        Collectors.summingDouble(MonthlyConsumption::getConsumption)));
        return new EnergyConsumed(energyTypeToConsumptionMap.getOrDefault(EnergyType.ELECTRICITY, 0d),
                energyTypeToConsumptionMap.getOrDefault(EnergyType.GAS, 0d));
    }
}
