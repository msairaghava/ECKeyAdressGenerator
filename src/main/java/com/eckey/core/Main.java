package com.eckey.core;

import com.eckey.core.wallet.Wallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    //configs
    public static Boolean hasExtraEntropy = false;
    public static final int SEED_ENTROPY_DEFAULT = 192;
    public static final int SEED_ENTROPY_EXTRA = 256;

    public static void main(String[] args) {
        System.out.println(generateNewMnemonic());
    }

    private static String generateNewMnemonic() {
        log.info("Generate a new mnemonic");
        String mnemonic;
        if (hasExtraEntropy) {
            mnemonic = Wallet.generateMnemonicString(SEED_ENTROPY_EXTRA);
        } else {
            mnemonic = Wallet.generateMnemonicString(SEED_ENTROPY_DEFAULT);
        }
        return mnemonic;
    }
}
