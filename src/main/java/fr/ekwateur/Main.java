package fr.ekwateur;

import fr.ekwateur.adapters.in.GetClientBillAmountCommandLine;
import fr.ekwateur.adapters.out.DefaultEnergyTariffProvider;
import fr.ekwateur.adapters.out.GetClientByReferenceInMemory;
import fr.ekwateur.application.port.in.GetClientBillAmount;
import fr.ekwateur.application.port.out.EnergyTariffProvider;
import fr.ekwateur.application.port.out.GetClientByReference;
import fr.ekwateur.application.service.ClientBillService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        GetClientByReference getClientByReference = GetClientByReferenceInMemory.getInstance();
        EnergyTariffProvider energyTariffProvider = new DefaultEnergyTariffProvider();
        GetClientBillAmount clientBillService = new ClientBillService(getClientByReference, energyTariffProvider);
        GetClientBillAmountCommandLine getClientBillAmountCommandLine =
                new GetClientBillAmountCommandLine(clientBillService, new BufferedReader(new InputStreamReader(System.in)));
        getClientBillAmountCommandLine.getClientBillAmount();
    }
}