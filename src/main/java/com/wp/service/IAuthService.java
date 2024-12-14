package com.wp.service;

import com.wp.dto.DtoUser;
import com.wp.jwt.AuthRequest;
import com.wp.jwt.AuthResponse;

public interface IAuthService {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
}
