package com.globalsoftwaresupport.blockchain;

import com.globalsoftwaresupport.cryptocurrency.TransactionOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockChain {

    public static List<Block> blockChain;
    public static Map<String, TransactionOutput> UTXOs;

    public BlockChain() {
        BlockChain.UTXOs = new HashMap<>();
        BlockChain.blockChain = new ArrayList<>();
    }

    public void addBlock(Block block) {
        BlockChain.blockChain.add(block);
    }
}
