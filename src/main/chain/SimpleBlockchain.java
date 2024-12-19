
public class SimpleBlockchain {

  public static void main(String[] args) {
    Blockchain blockchain = new Blockchain();
    blockchain.addBlock("First Block Data in the chain");
    blockchain.addBlock("Second Block Data in the chain");
    blockchain.addBlock("Third Block Data in the chain");

    blockchain.displayBlockchain();
    System.out.println("is blockchain valid?? " + blockchain.isChainValid());
  }
}
