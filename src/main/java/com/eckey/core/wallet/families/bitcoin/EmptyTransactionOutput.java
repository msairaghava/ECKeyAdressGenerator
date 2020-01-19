package com.eckey.core.wallet.families.bitcoin;


import com.eckey.core.coins.CoinType;

import org.bitcoinj.core.*;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.utils.MonetaryFormat;
import org.bouncycastle.util.encoders.Hex;

/**
 * @author John L. Jegutanis
 */
public class EmptyTransactionOutput extends TransactionOutput {
    private static EmptyTransactionOutput instance =
            new EmptyTransactionOutput(new FakeNetworkParameters(), null, Coin.ZERO,
                    Hex.decode("76a914000000000000000000000000000000000000000088ac"));

    static class FakeNetworkParameters extends NetworkParameters {
        @Override
        public String getPaymentProtocolId() {
            return "";
        }

        @Override
        public void checkDifficultyTransitions(StoredBlock storedBlock, Block block, BlockStore blockStore) throws VerificationException, BlockStoreException {

        }

        @Override
        public Coin getMaxMoney() {
            return null;
        }

        @Override
        public Coin getMinNonDustOutput() {
            return null;
        }

        @Override
        public MonetaryFormat getMonetaryFormat() {
            return null;
        }

        @Override
        public String getUriScheme() {
            return null;
        }

        @Override
        public boolean hasMaxMoney() {
            return false;
        }

        @Override
        public BitcoinSerializer getSerializer(boolean b) {
            return null;
        }

        @Override
        public int getProtocolVersionNum(ProtocolVersion protocolVersion) {
            return 0;
        }
    }

    private EmptyTransactionOutput(NetworkParameters params, Transaction parent, Coin value, byte[] scriptBytes) {
        super(params, parent, value, scriptBytes);
    }

    public static synchronized EmptyTransactionOutput get() {
        return instance;
    }

    @Override
    public int getIndex() {
        throw new IllegalArgumentException("Empty outputs don't have indexes");
    }
}

