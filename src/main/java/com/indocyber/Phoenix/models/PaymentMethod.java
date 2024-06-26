package com.indocyber.Phoenix.models;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CC("Credit Card"),
    DD("Direct Debit"),
    VO("Voucher"),
    EM("Electronic Money"),
    CA("Cash");
    private final String label;

    PaymentMethod(String label) {
        this.label = label;
    }
}
