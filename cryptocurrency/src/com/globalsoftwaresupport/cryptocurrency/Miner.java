package com.globalsoftwaresupport.cryptocurrency;

import com.globalsoftwaresupport.blockchain.Block;
import com.globalsoftwaresupport.blockchain.BlockChain;
import com.globalsoftwaresupport.constants.Constants;

public class Miner {

    private double reward;


    public void mine(Block block, BlockChain blockChain) {
        //PoW
        //it is not energy efficient
        while (!isGoldenHash(block)) {
            block.incrementNonce();
            block.generateHash();
        }

        System.out.println(block + " has just mined...");
        System.out.println("Hash is: " + block.getHash());

        blockChain.addBlock(block);
        reward += Constants.MINER_REWARD;
    }

    public boolean isGoldenHash(Block block) {
        String leadingZeros = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
        return block.getHash().substring(0, Constants.DIFFICULTY).equals(leadingZeros);
    }

    public double getReward() {
        return reward;
    }
}
