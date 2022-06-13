package wxm.tank;

import wxm.tank.constant.DirectionEnum;

import java.awt.*;

/**
 * @author wenxiangmin
 * @ClassName Bullet.java
 * @Description 子弹类
 * @createTime 2022年06月12日 21:48:00
 */
public abstract class Bullet {
    //速度
    int speed;

    //坐标
    int x,y;

    //半径
    int RADIUS;

    DirectionEnum dir;

    Color color = Color.WHITE;

    public void fly(Graphics g,  DirectionEnum tankDir) {
        g.setColor(color);
        if (DirectionEnum.UP == dir) {
            y = y - speed;
            g.fillOval(x - RADIUS,y - RADIUS,2 * RADIUS, 2 * RADIUS);
        } else if (DirectionEnum.DOWN == dir) {
            y = y + speed;
            g.fillOval(x - RADIUS,y - RADIUS,2 * RADIUS, 2 * RADIUS);
        } else if (DirectionEnum.LEFT == dir) {
            x = x - speed;
            g.fillOval(x - RADIUS,y - RADIUS,2 * RADIUS, 2 * RADIUS);
        } else if (DirectionEnum.RIGHT == dir) {
            x = x + speed;
            g.fillOval(x - RADIUS,y - RADIUS,2 * RADIUS, 2 * RADIUS);
        }
    }
}
