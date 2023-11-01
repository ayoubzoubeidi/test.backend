package fr.ekwateur.adapters.in;

import fr.ekwateur.application.port.in.GetClientBillAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GetClientBillAmountCommandLineTest {

    @Mock
    GetClientBillAmount GetClientBillAmount;
    @Mock
    BufferedReader bufferedReader;
    GetClientBillAmountCommandLine getClientBillAmountCommandLine;
    ByteArrayOutputStream myOut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        getClientBillAmountCommandLine = new GetClientBillAmountCommandLine(GetClientBillAmount, bufferedReader);
    }

    @Test
    public void testGetClientBillAmount_WithExistingClient() throws IOException {
        when(GetClientBillAmount.billAmount(anyString())).thenReturn(new BigDecimal("55"));
        when(bufferedReader.readLine()).thenReturn("", "1");

        getClientBillAmountCommandLine.getClientBillAmount();

        //assertEquals("The bill amount for " + "1" + " is " + clientBillAmount + " euros", standardOutput);
    }

}