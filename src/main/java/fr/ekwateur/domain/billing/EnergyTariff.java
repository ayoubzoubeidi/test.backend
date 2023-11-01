package fr.ekwateur.domain.billing;

import java.math.BigDecimal;

public record EnergyTariff(BigDecimal electricityPrice, BigDecimal gasPrice) {
}
