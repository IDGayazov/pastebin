package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256HashGenerator implements HashGenerator{
    
	private static final Sha256HashGenerator INSTANCE = new Sha256HashGenerator();
	
	private Sha256HashGenerator() {
	}
	
	public String generate(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(data.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static Sha256HashGenerator getInstance() {
		return INSTANCE;
	}
	
}
