package com.eckey.core.util;

import com.eckey.core.Main;
import com.eckey.core.wallet.Wallet;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CoreUtils {
    private static final Logger log = LoggerFactory.getLogger(CoreUtils.class);

    //configs
    public static Boolean hasExtraEntropy = false;
    public static final int SEED_ENTROPY_DEFAULT = 192;
    public static final int SEED_ENTROPY_EXTRA = 256;

    public static ArrayList<String> parseMnemonic(String mnemonicString) {
        ArrayList<String> seedWords = new ArrayList<>();
        for (String word : mnemonicString.trim().split(" ")) {
            if (word.isEmpty()) continue;
            seedWords.add(word);
        }
        return seedWords;
    }

    public static String generateNewMnemonic() {
        log.info("Generate a new mnemonic");
        String mnemonic;
        if (hasExtraEntropy) {
            mnemonic = Wallet.generateMnemonicString(SEED_ENTROPY_EXTRA);
        } else {
            mnemonic = Wallet.generateMnemonicString(SEED_ENTROPY_DEFAULT);
        }
        return mnemonic;
    }

    public static boolean verifyMnemonic(String seed) {
        log.info("Verifying seed");
        ArrayList<String> seedWords = CoreUtils.parseMnemonic(seed);
        boolean isSeedValid = false;
        try {
            MnemonicCode.INSTANCE.check(seedWords);
            isSeedValid = true;
        } catch (MnemonicException.MnemonicChecksumException e) {
            log.error("Checksum error in seed: {}", e.getMessage());
        } catch (MnemonicException.MnemonicWordException e) {
            log.error("Unknown words in seed: {}", e.getMessage());
        } catch (MnemonicException e) {
            log.error("Error verifying seed: {}", e.getMessage());
        }
//        Ask user to re-enter seed word just to verify he secured it properly
//        if (isSeedValid && seed != null) {
//            log.info("Typed seed does not match the generated one.");
//            setError(errorMnemonicΜessage, R.string.restore_error_mismatch);
//            isSeedValid = false;
//        }
        return isSeedValid;
    }

    public static Boolean checkPasswordQuality(String password) {
        Boolean isPasswordGood = false;
        try {
            PasswordQualityChecker.checkPassword(password);
            isPasswordGood = true;
        } catch (PasswordQualityChecker.PasswordTooCommonException e1) {
            log.info("Entered a too common password {}", password);
        } catch (PasswordQualityChecker.PasswordTooShortException e2) {
            log.info("Entered a too short password");
        }
        log.info("Password good = {}", isPasswordGood);
        return isPasswordGood;
    }

}
