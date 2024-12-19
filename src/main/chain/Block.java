import java.security.MessageDigest;
import java.util.Date;

public class Block {

  private final int index;
  private final String timestamp;
  private final String data;
  private final String previousHash;
  private final String hash;
  public static int count = 0;

  public Block(String data, String previousHash) {
    this.index = count++;
    this.timestamp = "111234224";
    this.data = data;
    this.previousHash = previousHash;
    this.hash = calculateHash();
  }

  // Overloaded constructor for fixed timestamps
  public Block(String data, String previousHash, String fixedTimestamp) {
    this.index = count++;
    this.timestamp = fixedTimestamp;
    this.data = data;
    this.previousHash = previousHash;
    this.hash = calculateHash();
  }

  public int getIndex() {
    return index;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getData() {
    return data;
  }

  public String getPreviousHash() {
    return previousHash;
  }

  public String getHash() {
    return hash;
  }

  private String calculateHash() {
    String blockData = index + timestamp + data + previousHash;
    return applySHA256(blockData);
  }

  private String applySHA256(String input) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(input.getBytes());
      StringBuilder hexString = new StringBuilder();
      for (byte b : hash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isHashValid() {
    return this.hash.equals(calculateHash());
  }
}
