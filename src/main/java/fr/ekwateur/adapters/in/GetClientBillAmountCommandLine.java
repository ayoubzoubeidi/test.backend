package fr.ekwateur.adapters.in;

import fr.ekwateur.application.port.in.GetClientBillAmount;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.math.BigDecimal;

public class GetClientBillAmountCommandLine {

    private final GetClientBillAmount getClientBillAmount;
    private final BufferedReader reader;

    public GetClientBillAmountCommandLine(GetClientBillAmount getClientBillAmount, BufferedReader reader) {
        this.getClientBillAmount = getClientBillAmount;
        this.reader = reader;
    }

    @SneakyThrows
    public void getClientBillAmount() {
        System.out.println("Hello, please enter the client reference\n");
        String clientReference = reader.readLine();
        try {
            BigDecimal clientBillAmount = getClientBillAmount.billAmount(clientReference);
            System.out.println("The bill amount for " + clientReference + " is " + clientBillAmount + " euros");
        } catch (RuntimeException e) {
            System.out.println("Client Not Found");
        }
    }
}
