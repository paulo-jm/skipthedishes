package com.skipthedishes.service.auth;

import java.io.Serializable;

import javax.ejb.Local;

import com.skipthedishes.service.auth.exception.AuthException;

@Local
public interface AuthServiceLocal extends Serializable{

	
	public void auth(String email, String password) throws AuthException;
	
}






