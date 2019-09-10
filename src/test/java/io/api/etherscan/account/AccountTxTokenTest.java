package io.api.etherscan.account;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.model.TxToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AccountTxTokenTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        List<TxToken> txs = api.account().txsToken("0xE376F69ED2218076682e2b3B7b9099eC50aD68c4");
        assertNotNull(txs);
        assertEquals(3, txs.size());
        assertTxs(txs);
        assertNotEquals(0, txs.get(0).getGasPrice());
        assertNotEquals(-1, txs.get(0).getNonce());

        assertNotNull(txs.get(0).toString());
        assertNotEquals(txs.get(0).toString(), txs.get(1).toString());

        assertNotEquals(txs.get(0), txs.get(1));
        assertNotEquals(txs.get(0).hashCode(), txs.get(1).hashCode());

        assertEquals(txs.get(1), txs.get(1));
        assertEquals(txs.get(1).hashCode(), txs.get(1).hashCode());
    }

    @Test
    public void correctStartBlock() {
        List<TxToken> txs = api.account().txsToken("0x36ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7", 5578167);
        assertNotNull(txs);
        assertEquals(11, txs.size());
        assertTxs(txs);
    }

    @Test
    public void correctStartBlockEndBlock() {
        List<TxToken> txs = api.account().txsToken("0x36ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7", 5578167, 5813576);
        assertNotNull(txs);
        assertEquals(5, txs.size());
        assertTxs(txs);
    }

    @Test(expected = InvalidAddressException.class)
    public void invalidParamWithError() {
        api.account().txsToken("0x6ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7");
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        List<TxToken> txs = api.account().txsToken("0x31ec53A8fBa6358d59B3C4476D82cc60A2B0FaD7");
        assertNotNull(txs);
        assertTrue(txs.isEmpty());
    }

    private void assertTxs(List<TxToken> txs) {
        for (TxToken tx : txs) {
            assertNotNull(tx.getBlockHash());
            assertNotNull(tx.getTokenName());
            assertNotNull(tx.getTokenSymbol());
            assertNotNull(tx.getFrom());
            assertNotNull(tx.getTo());
            assertNotNull(tx.getTimeStamp());
            assertNotNull(tx.getTokenDecimal());
            assertNotEquals(-1,(tx.getConfirmations()));
            assertNotNull(tx.getGasUsed());
            assertNotEquals(-1 ,tx.getCumulativeGasUsed());
            assertNotEquals(-1, tx.getTransactionIndex());
        }
    }
}
