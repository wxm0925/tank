package wxm.tank.util;

import java.awt.*;

/**
 * @author wenxiangmin
 * @ClassName Utils.java
 * @Description 工具类
 * @createTime 2022年06月12日 19:05:00
 */
public class Utils {
    /**
     * 返回指定区间的随机数
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public static final int getRandomNumber(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }

    /**
     * 返回随机的颜色
     * @return
     */
    public static final Color getRandomColor() {
        int r = getRandomNumber(0,255);
        int g = getRandomNumber(0,255);
        int b = getRandomNumber(0,255);
        return new Color(r,g,b);
    }
}
