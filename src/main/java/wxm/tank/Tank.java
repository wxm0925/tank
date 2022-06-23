package wxm.tank;

import wxm.tank.constant.DirectionEnum;
import wxm.tank.constant.TankConstants;
import wxm.tank.frame.MainFrame;
import wxm.tank.util.BulletPool;
import wxm.tank.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenxiangmin
 * @ClassName Tank.java
 * @Description 坦克类
 * @createTime 2022年06月12日 18:44:00
 */
public abstract class Tank {
    //坦克图片的宽高
    public static final int WIDTH = TankConstants.TANK_WIDTH;
    public static final int HIGHT = TankConstants.TANK_HEIGHT;


    //状态
    public static final int STATE_STANDING = 0;
    public static final int STATE_MOVING = 1;
    public static final int STATE_DEAD = 2;

    //默认速度 每帧4像素，目前的刷新率是每秒33帧
    static final int DEFAULT_SPEED = 5 ;

    public int state = STATE_STANDING;
    //坐标
    public int x,y;

    //血量
    public int hp = 1000;

    //攻击力
    public int atk;

    //速度
    public int speed = DEFAULT_SPEED;

    //当前的方向
    public DirectionEnum dir;


    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    //敌人坦克为true
    public boolean enemy;



    /**
     * 构造器
     * @param x 坐标x
     * @param y 坐标y
     * @param dir 方向
     */
    public Tank(int x, int y,DirectionEnum dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }


    /**
     * 绘制坦克
     * @param g 画笔
     */
    public abstract void draw(Graphics g);

    public void launch(Graphics g) {
        Bullet bullet = BulletPool.get();
        bullet.setX(x);
        bullet.setY(y);
        bullet.setDir(dir);
        bullet.setVisible(true);
        bullets.add(bullet);
    }

    /**
     * 创建敌人坦克
     * @param dir 方向
     * @return
     */
//    public static Tank createEnemyTank(DirectionEnum dir) {
//        Tank tank = new Tank(dir);
//        tank.setY(Tank.HIGHT / 2 + MainFrame.titleHight);
//        tank.setX(Tank.WIDTH / 2 + (Utils.getRandomNumber(1,TankConstants.FRAME_WIDTH)));
//        tank.setEnemy(true);
//        return tank;
//    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public DirectionEnum getDir() {
        return dir;
    }

    public void setDir(DirectionEnum dir) {
        this.dir = dir;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }
}
