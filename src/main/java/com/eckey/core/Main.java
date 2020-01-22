package com.eckey.core;

import com.eckey.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String newSeed = CoreUtils.generateNewMnemonic();// seed value to be glovbally set in constants file to access it across various modules
        if(CoreUtils.verifyMnemonic(newSeed)){
            log.info("Seed verified successfully");
        }
    }
}
