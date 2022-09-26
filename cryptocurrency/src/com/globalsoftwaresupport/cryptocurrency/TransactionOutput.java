package com.globalsoftwaresupport.cryptocurrency;

import java.security.PublicKey;

public class TransactionOutput {

    private String id;
    private String parentTransactionId;
    private PublicKey receiver;
    private double amount;

    public TransactionOutput(String parentTransactionId, PublicKey receiver, double amount) {
        this.parentTransactionId = parentTransactionId;
        this.receiver = receiver;
        this.amount = amount;
        generateId();
    }

    private void generateId() {
        this.id = CryptographyHelper.hash(receiver.toString() + amount + parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey) {
        return publicKey == receiver;
    }

    public String getId() {
        return id;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public void setReceiver(PublicKey receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
