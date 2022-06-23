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

    boolean visible = true;

    DirectionEnum dir;

    Color color = Color.WHITE;

    /**
     * 绘制子弹
     * @param g
     * @param tankDir
     */
    public abstract void fly(Graphics g,  DirectionEnum tankDir);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionEnum getDir() {
        return dir;
    }

    public void setDir(DirectionEnum dir) {
        this.dir = dir;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
