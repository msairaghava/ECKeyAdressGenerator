package com.eckey.core;

import org.bitcoinj.core.ECKey;

import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
        SecureRandom random  = new SecureRandom();
        ECKey eckey = new ECKey(random);
        System.out.println("Private Key: " + eckey.getPrivateKeyAsHex());
        System.out.println("Public Key: " + eckey.getPublicKeyAsHex());
    }
}
