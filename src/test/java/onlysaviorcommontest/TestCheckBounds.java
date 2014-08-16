package onlysaviorcommontest;

import onlysaviorcommon.util.Assert;
import org.junit.Test;

/**
 * Created by onlysavior on 14-8-16.
 */
public class TestCheckBounds {
    @Test
    public void testCheckBound() {
        int offset = 10;
        int length = 2;
        int dstLength = 13;

        Assert.assertTure(!((offset | length | (offset + length) | (dstLength - (offset + length))) < 0));
    }
}
