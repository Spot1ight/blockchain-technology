package com.company;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // BLockchain contruction
     /*   BlockChain blockChain = new BlockChain();
        Miner miner = new Miner();

        Block block0 = new Block(0, "", Constants.GENESIS_PREV_HASH);
        miner.mine(block0, blockChain);

        Block block1 = new Block(1, "transaction1", blockChain
                .getBlockChain()
                .get(blockChain.getSize() - 1)
                .getHash());
        miner.mine(block1, blockChain);

        Block block2 = new Block(2, "transaction2", blockChain
                .getBlockChain()
                .get(blockChain.getSize() - 1)
                .getHash());
        miner.mine(block2, blockChain);

        System.out.println("\n" + "BLOCKCHAIN:\n" + blockChain);
        System.out.println("Miner's reward: " + miner.getReward());*/


        // MerkleRoot construction
        List<String> transactions = new ArrayList<>();
        transactions.add("aa");
        transactions.add("bb");
        transactions.add("dd");
        transactions.add("transaction4");
        transactions.add("7777");
        transactions.add("asf");
        MerkleTree merkleTree = new MerkleTree(transactions);
        System.out.println(merkleTree.getMerkleRoot());


    }
}