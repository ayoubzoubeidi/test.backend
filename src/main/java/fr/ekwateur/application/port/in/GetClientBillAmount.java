package fr.ekwateur.application.port.in;

import java.math.BigDecimal;

public interface GetClientBillAmount {
    BigDecimal billAmount(String clientReference);
}
