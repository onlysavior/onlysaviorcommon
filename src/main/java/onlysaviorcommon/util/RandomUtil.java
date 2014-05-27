package onlysaviorcommon.util;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yanye.lj on 13-12-16.
 */
public class RandomUtil {
    private static final Logger logger = Logger.getLogger(RandomUtil.class);
    /**
     * 假设三个库权重    10   9   8
     * 那么areaEnds就是  10  19  27
     * 随机数是0~27之间的一个数
     *
     * 分别去上面areaEnds里的元素比。
     *
     * 发现随机数小于一个元素了，则表示应该选择这个元素
     *
     * 注意：该方法不能改变参数数组内容
     */
    private final Random random = new Random();
    private String select(int[] areaEnds, String[] keys){
        int sum = areaEnds[areaEnds.length - 1];
        if(sum == 0) {
            return null;
        }
        //选择的过
        int rand = random.nextInt(sum);
        for(int i = 0; i < areaEnds.length; i++) {
            if(rand < areaEnds[i]) {
                return keys[i];
            }
        }
        return null;
    }
}
