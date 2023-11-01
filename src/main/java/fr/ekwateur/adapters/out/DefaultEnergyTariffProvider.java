package fr.ekwateur.adapters.out;

import fr.ekwateur.application.domain.billing.EnergyTariff;
import fr.ekwateur.application.port.out.EnergyTariffProvider;
import fr.ekwateur.application.domain.client.Client;
import fr.ekwateur.application.domain.client.ClientType;
import fr.ekwateur.application.domain.client.ProClient;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DefaultEnergyTariffProvider implements EnergyTariffProvider {

    public static String PARTICULAR_UNIT_ELECTRICITY_PRICE = "0.121";
    public static String PARTICULAR_UNIT_GAS_PRICE = "0.115";
    public static String PRO_TURNOVER_CAP = "1000000";
    public static String PRO_BIG_UNIT_ELECTRICITY_PRICE = "0.114";
    public static String PRO_BIG_UNIT_GAS_PRICE = "0.111";
    public static String PRO_SMALL_UNIT_ELECTRICITY_PRICE = "0.118";
    public static String PRO_SMALL_UNIT_GAS_PRICE = "0.113";


    private final Map<ClientType, Function<Client, EnergyTariff>> clientTariffResolver
            = new HashMap<>();

    {
        clientTariffResolver.put(ClientType.INDIVIDUAL, new GetIndividualClientEnergyTariff());
        clientTariffResolver.put(ClientType.PRO, new GetProClientEnergyTariff());
    }

    @Override
    public EnergyTariff getEnergyTariff(Client client) {
        return clientTariffResolver.get(client.getClientType()).apply(client);
    }

    private static class GetIndividualClientEnergyTariff implements Function<Client, EnergyTariff> {
        @Override
        public EnergyTariff apply(Client client) {
            return new EnergyTariff(new BigDecimal(PARTICULAR_UNIT_ELECTRICITY_PRICE),
                    new BigDecimal(PARTICULAR_UNIT_GAS_PRICE));
        }
    }

    private static class GetProClientEnergyTariff implements Function<Client, EnergyTariff> {
        @Override
        public EnergyTariff apply(Client client) {
            ProClient proClient = (ProClient) client;
            BigDecimal turnoverCap = new BigDecimal(PRO_TURNOVER_CAP);

            if (proClient.getTurnover().compareTo(turnoverCap) > 0) {
                return new EnergyTariff(new BigDecimal(PRO_BIG_UNIT_ELECTRICITY_PRICE),
                        new BigDecimal(PRO_BIG_UNIT_GAS_PRICE));
            }
            return new EnergyTariff(new BigDecimal(PRO_SMALL_UNIT_ELECTRICITY_PRICE),
                    new BigDecimal(PRO_SMALL_UNIT_GAS_PRICE));
        }
    }
}
