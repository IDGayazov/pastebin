package security;

import hash.Sha256HashGenerator;

public class Sha256PasswordEncoder implements PasswordEncoder {
	
	private static final Sha256HashGenerator hashGenerator = Sha256HashGenerator.getInstance();
	private static final Sha256PasswordEncoder INSTANCE = new Sha256PasswordEncoder();
	
	private Sha256PasswordEncoder() {
		
	}
	
	@Override
	public String encode(String str) {
		return hashGenerator.generate(str);
	}

	public static Sha256PasswordEncoder getInstance() {
		return INSTANCE;
	}
}
