import org.apache.commons.math3.distribution.NormalDistribution;
import org.junit.Test;

import java.util.Arrays;

/**
 * User: maciek
 * Date: 11.10.13
 * Time: 12:36
 */

public class NormalDistrTest {

    @Test
    public void testMultiply() {

        NormalDistribution normalDistribution = new NormalDistribution();

        System.out.println(normalDistribution.sample());
        System.out.println(Arrays.toString(normalDistribution.sample(10)));
        System.out.println(Arrays.toString(normalDistribution.sample(10)));

    }
}
