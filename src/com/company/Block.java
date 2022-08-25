package com.company;

import java.util.Date;

public class Block {
    private final int id;
    private int nonce;
    private final long timestamp;
    private String hash;
    private String previousHash;
    private final String transaction;

    public Block(int id, String transaction, String previousHash) {
        this.id = id;
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        generateHash();
    }

    public void generateHash() {
        String dataToHash = id + previousHash + timestamp + nonce + transaction;
        this.hash = SHA256Helper.hash(dataToHash);
    }

    public void incrementNonce() {
        this.nonce++;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", nonce='" + nonce + '\'' +
                ", hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", transaction='" + transaction + '\'' +
                '}';
    }
}
