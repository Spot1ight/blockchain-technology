package com.globalsoftwaresupport.cryptocurrency;

import com.globalsoftwaresupport.blockchain.BlockChain;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String transactionId;
    private PublicKey sender;
    private PublicKey receiver;
    private double amount;
    private byte[] signature;

    public List<TransactionInput> inputs;
    public List<TransactionOutput> outputs;

    public Transaction(PublicKey sender,
                       PublicKey receiver,
                       double amount,
                       List<TransactionInput> inputs) {
        this.outputs = new ArrayList<>();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.inputs = inputs;
        calculateHash();
    }

    public boolean verifyTransaction() {
        if (!verifySignature()) {
            System.out.println("Invalid transaction because of invalid signature...");
            return false;
        }

        inputs.forEach(
                input -> input.setUTXO(BlockChain.UTXOs.get(input.getTransactionOutputId()))
        );

        outputs.add(new TransactionOutput(transactionId, receiver, amount));
        outputs.add(new TransactionOutput(transactionId, sender, getInputSum() - amount));

        outputs.forEach(
                output -> BlockChain.UTXOs.put(output.getId(), output)
        );

        inputs.forEach(
                        input -> {
                            if (input.getUTXO() != null) {
                                BlockChain.UTXOs.remove(input.getUTXO().getId());
                            }
                        }
                );
        return true;
    }

    public double getInputSum() {
        return inputs
                .stream()
                .filter(input -> input.getUTXO() != null)
                .mapToDouble(input -> input.getUTXO().getAmount())
                .sum();
    }

    private void calculateHash() {
        String hashData = sender.toString() + receiver.toString() + amount;
        this.transactionId = CryptographyHelper.hash(hashData);
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = sender.toString() + receiver.toString() + amount;
        signature = CryptographyHelper.sign(privateKey, data);
    }

    public boolean verifySignature() {
        String data = sender.toString() + receiver.toString() + amount;
        return CryptographyHelper.verify(sender, data, signature);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PublicKey getSender() {
        return sender;
    }

    public void setSender(PublicKey sender) {
        this.sender = sender;
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

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public List<TransactionInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<TransactionInput> inputs) {
        this.inputs = inputs;
    }

    public List<TransactionOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<TransactionOutput> outputs) {
        this.outputs = outputs;
    }
}
