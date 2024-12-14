package com.wp.controller;

import com.wp.dto.DtoUser;
import com.wp.jwt.AuthRequest;
import com.wp.jwt.AuthResponse;
import com.wp.jwt.RefreshTokenRequest;

public interface IRestAuthController {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
}
