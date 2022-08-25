package com.company;

public class Miner {
    private double reward;

    public void mine(Block block, BlockChain blockChain) {
        // it takes
        while (!isGoldenHash(block)) {
            //System.out.println(block);
            block.incrementNonce();
            block.generateHash();
        }

        System.out.println(block + " has just mined...");
        System.out.println("Hash is: " + block.getHash());

        blockChain.addBlock(block);
        reward += Constants.REWARD;
    }

    private boolean isGoldenHash(Block block) {
        String leadingZero = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
        return block.getHash().substring(0, Constants.DIFFICULTY).equals(leadingZero);
    }

    public double getReward() {
        return this.reward;
    }
}
