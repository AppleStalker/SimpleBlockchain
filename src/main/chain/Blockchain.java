import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Blockchain
 */
public class Blockchain {

  private final List<Block> blockchain;

  public Blockchain() {
    blockchain = new ArrayList<>();
    blockchain.add(createGenesisBlock());
  }

  private Block createGenesisBlock() {
    return new Block("genesis block", "0", "12243334343");
  }

  public void addBlock(String data) {
    Block lastBlock = blockchain.get(blockchain.size() - 1);
    blockchain.add(new Block(data, lastBlock.getHash()));
  }

  public boolean isChainValid() {
    for (int i = 1; i < blockchain.size(); i++) {
      Block currentBlock = blockchain.get(i);
      Block previousBlock = blockchain.get(i - 1);
      if (!currentBlock.isHashValid()) {
        System.out.println("Invalid hash detected at block " + currentBlock.getIndex());
        return false;
      }

      if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
        System.out.println("Broken chain detected between blocks " + (i - 1) + " and " + i);
        return false;
      }
    }
    return true;
  }

  public void displayBlockchain() {
    System.out.println("=== Blockchain ===");
    for (Block block : blockchain) {
      System.out.println("Block " + block.getIndex() + ":");
      System.out.println("    Timestamp: " + block.getTimestamp());
      System.out.println("    Data: " + block.getData());
      System.out.println("    Previous Hash: " + block.getPreviousHash());
      System.out.println("    Hash: " + block.getHash());
      System.out.println();
    }
  }

  public List<Block> getBlockchain() {
    return Collections.unmodifiableList(blockchain);
  }
}
