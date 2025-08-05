package com.example.msaproject.paymentservice.entity;

public enum PaymentMethod {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    PAYPAL("PayPal"),
    STRIPE("Stripe"),
    BANK_TRANSFER("Bank Transfer"),
    UPI("UPI"),
    WALLET("Digital Wallet"),
    NET_BANKING("Net Banking");
    
    private final String displayName;
    
    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
