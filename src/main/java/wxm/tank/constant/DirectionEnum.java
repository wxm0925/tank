package wxm.tank.constant;

import java.awt.*;

/**
 * @author wenxiangmin
 * @ClassName DirectionEnum.java
 * @Description 方向枚举
 * @createTime 2022年06月13日 21:18:00
 */
public enum DirectionEnum {
    UP(1),DOWN(2),LEFT(2),RIGHT(3);

    private int index;

    DirectionEnum(int index) {
        this.index = index;
    }

}
