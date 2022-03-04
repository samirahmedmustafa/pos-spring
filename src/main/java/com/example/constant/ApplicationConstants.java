package com.example.constant;

public class ApplicationConstants {
	public static final long EXPIRATION_TIME = 432_000_000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String JWT_TOKEN = "access_token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String USER_NOT_FOUND = "User not found";
	public static final String ROLE_NOT_FOUND = "Role not found";
	public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
	public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
	public static final String AUTHORITIES = "Authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in order to access this page";
	public static final String ACCESS_DENIED_MESSAGE = "You don not have permission to access this page";
	public static final String SECRET = "#@!#$GWERTG#$GRGBHRT#$%T";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String[] PUBLIC_URLS = {"/api/login", "/api/login/refreshToken", "/api/register", "/api/resetpassword/**", "/api/images/**"};
}
