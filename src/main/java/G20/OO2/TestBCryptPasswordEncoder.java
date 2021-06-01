package G20.OO2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("admin"));
		
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		System.out.println(p.encode("audit"));

	}
}
