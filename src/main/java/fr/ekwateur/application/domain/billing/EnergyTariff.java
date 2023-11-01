package fr.ekwateur.application.domain.billing;

import java.math.BigDecimal;

public record EnergyTariff(BigDecimal electricityPrice, BigDecimal gasPrice) {
}
