package com.company;

import java.util.ArrayList;
import java.util.List;

public class MerkleTree {

    private List<String> transactions;

    public MerkleTree(List<String> transactions) {
        this.transactions = transactions;
    }

    public String getMerkleRoot() {
        return construct(this.transactions).get(0);
    }

    private List<String> construct(List<String> transactions) {

        if (transactions.size() == 1) return transactions;

        List<String> updatedList = new ArrayList<>();

        for (int i = 0; i < transactions.size() - 1; i+=2 ) {
            updatedList.add(mergeHash(transactions.get(i), transactions.get(i+1)));

            if (transactions.size()%2==1) {
                updatedList.add(mergeHash(transactions.get(transactions.size() - 1), transactions.get(transactions.size() - 1)));
            }
        }

        return construct(updatedList);
    }



    private String mergeHash(String hash1, String hash2) {
        String mergedHash = hash1 + hash2;
        return CryptographyHelper.hash(mergedHash);
    }


}
