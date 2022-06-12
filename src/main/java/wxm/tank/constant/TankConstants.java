package wxm.tank.constant;

import java.awt.*;

/**
 * @author wenxiangmin
 * @ClassName Constants.java
 * @Description TODO
 * @createTime 2022年06月10日 19:58:00
 */
public interface TankConstants {
    String title = "坦克大战";

    //主窗口宽度
    int FRAME_WIDTH = 1500;
    //主窗口宽度
    int FRAME_HIGHT = 900;

    //屏幕轴的 X Y轴。定义主窗口在屏幕的显示位置
    int LOCATION_X = (2160 / 2)  - (FRAME_WIDTH / 2);
    int LOCATION_Y = (1440 / 2)  - (FRAME_HIGHT / 2);

    //游戏状态
    int STATE_MENU = 0;
    int STATE_HELP = 1;
    int STATE_ABOUT = 2;
    int STATE_IN_GAME = 3;
    int STATE_EXIT = 4;


    String[] MENU = {
            "开始游戏",
            "继续游戏",
            "游戏帮助",
            "游戏关于",
            "退出游戏"
    };

    Font FONT = new Font("宋体",Font.BOLD,34);
}
