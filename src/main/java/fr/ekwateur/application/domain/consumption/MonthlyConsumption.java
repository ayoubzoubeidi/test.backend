package fr.ekwateur.application.domain.consumption;

import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter
public class MonthlyConsumption {

    protected EnergyType energyType;
    private YearMonth yearMonth;
    private double consumption;

    public MonthlyConsumption(EnergyType energyType, YearMonth yearMonth, double consumption) {
        this.energyType = energyType;
        this.yearMonth = yearMonth;
        this.consumption = consumption;
    }
}
