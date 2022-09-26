package com.globalsoftwaresupport.blockchain;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.cryptocurrency.CryptographyHelper;
import com.globalsoftwaresupport.cryptocurrency.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {
    private int id;
    private int nonce;
    private long timestamp;
    private String hash;
    private String previousHash;
    public List<Transaction> transactions;

    public Block(String previousHash) {
        this.transactions = new ArrayList<>();
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        generateHash();
    }

    public void generateHash() {
        String dataToHash = id + previousHash + timestamp + transactions.toString() + nonce;
        this.hash = CryptographyHelper.hash(dataToHash);
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;

        if (!previousHash.equals(Constants.GENESIS_PREV_HASH) && !transaction.verifyTransaction()) {
            System.out.println("Transaction is not valid");
            return false;
        }

        transactions.add(transaction);
        System.out.println("Transaction is valid and it's added to the block " + this);
        return true;
    }

    public void incrementNonce() {
        this.nonce++;
    }

    public String getHash() {
        return hash;
    }
}
