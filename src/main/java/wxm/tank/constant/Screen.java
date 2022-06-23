package wxm.tank.constant;

import java.awt.*;

/**
 * @author wenxiangmin
 * @ClassName Screen.java
 * @Description 当前屏幕宽高
 * @createTime 2022年06月23日 11:20:00
 */
public class Screen {
    public static final int WIDTH;

    public static final int HEIGHT;

    public static final int COLUMN_NUM;

    public static final int ROW_NUM;



    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        WIDTH = scrnsize.width;
        HEIGHT = scrnsize.height;

        COLUMN_NUM = WIDTH / TankConstants.TANK_WIDTH;
        ROW_NUM = WIDTH / TankConstants.TANK_WIDTH;

    }
}
