package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.proxy.BlockProxy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyBlockApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        Optional<BlockProxy> block = api.proxy().block(5120);
        assertTrue(block.isPresent());
        BlockProxy proxy = block.get();
        assertNotNull(proxy.getHash());
        assertNotNull(proxy.getNumber());
        assertNotNull(proxy.getParentHash());
        assertNotNull(proxy.getStateRoot());
        assertNotNull(proxy.getSize());
        assertNotNull(proxy.getDifficulty());
        assertNotNull(proxy.getTotalDifficulty());
        assertNotNull(proxy.getTimeStamp());
        assertNotNull(proxy.getMiner());
        assertNotNull(proxy.getNonce());
        assertNotNull(proxy.getHash());
        assertNotNull(proxy.getExtraData());
        assertNotNull(proxy.getLogsBloom());
        assertNotNull(proxy.getMixHash());
        assertNotNull(proxy.getGasUsed());
        assertNotNull(proxy.getGasLimit());
        assertNotNull(proxy.getSha3Uncles());
        assertNotNull(proxy.getTransactions());
        assertNotNull(proxy.getTransactionsRoot());
        assertNotNull(proxy.getReceiptsRoot());
        assertNotNull(proxy.getUncles());
        assertNotNull(proxy.toString());

        BlockProxy empty = new BlockProxy();
        assertNotEquals(proxy, empty);
        assertNotEquals(proxy.hashCode(), empty.hashCode());
    }

    @Test
    public void correctParamWithEmptyExpectedResult() {
        Optional<BlockProxy> block = api.proxy().block(99999999999L);
        assertFalse(block.isPresent());
    }

    @Test
    public void correctParamNegativeNo() {
        Optional<BlockProxy> block = api.proxy().block(-1);
        assertTrue(block.isPresent());
        assertNotNull(block.get().getHash());
    }
}
