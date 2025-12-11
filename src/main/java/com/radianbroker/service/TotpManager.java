package com.radianbroker.service;

public interface TotpManager {

	boolean verifyCode(String code, String secret);

}
