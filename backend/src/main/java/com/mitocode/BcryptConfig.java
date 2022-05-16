package com.mitocode;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptConfig {
	
	public BCryptPasswordEncoder passwordEnconder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();		
		return bCryptPasswordEncoder;
	}

}
