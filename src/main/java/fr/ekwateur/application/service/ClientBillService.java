package fr.ekwateur.application.service;

import fr.ekwateur.application.domain.billing.Invoice;
import fr.ekwateur.application.domain.client.Client;
import fr.ekwateur.application.port.in.GetClientBillAmount;
import fr.ekwateur.application.port.out.EnergyTariffProvider;
import fr.ekwateur.application.port.out.GetClientByReference;
import java.math.BigDecimal;
import java.time.YearMonth;

public class ClientBillService implements GetClientBillAmount {
    private final GetClientByReference getClientByReference;
    private final EnergyTariffProvider energyTariffProvider;

    public ClientBillService(GetClientByReference getClientByReference, EnergyTariffProvider energyTariffProvider) {
        this.getClientByReference = getClientByReference;
        this.energyTariffProvider = energyTariffProvider;
    }

    @Override
    public BigDecimal billAmount(String clientReference) {
        Client client = getClientByReference.getClientByReference(clientReference);
        Invoice invoice = new Invoice(client, energyTariffProvider);
        return invoice.getAmount(YearMonth.now());
    }
}
