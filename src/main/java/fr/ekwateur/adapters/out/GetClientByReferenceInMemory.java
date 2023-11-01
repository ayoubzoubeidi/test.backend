package fr.ekwateur.adapters.out;

import fr.ekwateur.application.domain.client.Client;
import fr.ekwateur.application.domain.client.ProClient;
import fr.ekwateur.application.domain.consumption.EnergyConsumption;
import fr.ekwateur.application.domain.consumption.EnergyType;
import fr.ekwateur.application.domain.consumption.MonthlyConsumption;
import fr.ekwateur.application.port.out.GetClientByReference;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetClientByReferenceInMemory implements GetClientByReference {

    private static GetClientByReferenceInMemory singletonReference;
    private List<Client> clients = new ArrayList<>();


    public static synchronized GetClientByReferenceInMemory getInstance() {
        if (singletonReference == null) {
            singletonReference = new GetClientByReferenceInMemory();
        }
        return singletonReference;
    }

    private GetClientByReferenceInMemory() {
        initInMemoryData();
    }


    @Override
    public Client getClientByReference(String clientReference) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getClientReference().equals(clientReference))
                .findFirst();
        return clientOptional.orElseThrow(() -> new RuntimeException("Client Not Found"));
    }

    private void initInMemoryData() {
        String reference1 = "EKW58612358";
        MonthlyConsumption monthlyConsumption1 =
                new MonthlyConsumption(EnergyType.ELECTRICITY, YearMonth.now(), 55.32d);
        MonthlyConsumption monthlyConsumption2 =
                new MonthlyConsumption(EnergyType.GAS, YearMonth.now(), 100.5d);
        EnergyConsumption energyConsumption1 =
                new EnergyConsumption();
        energyConsumption1.setMonthlyEnergyConsumptions(List.of(monthlyConsumption1, monthlyConsumption2));
        Client client = new ProClient(reference1, "SIRETTEST",
                "societe test", new BigDecimal("10000000"), energyConsumption1);
        clients.add(client);
    }
}
