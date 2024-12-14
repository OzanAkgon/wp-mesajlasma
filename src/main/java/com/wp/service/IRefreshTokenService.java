package com.wp.service;

import com.wp.jwt.AuthResponse;
import com.wp.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {

	public AuthResponse refreshToken(RefreshTokenRequest request);
}
