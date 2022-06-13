package wxm.tank;

import wxm.tank.constant.DirectionEnum;
import wxm.tank.constant.TankConstants;
import wxm.tank.frame.MainFrame;
import wxm.tank.util.Utils;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.Queue;

/**
 * @author wenxiangmin
 * @ClassName Tank.java
 * @Description 坦克类
 * @createTime 2022年06月12日 18:44:00
 */
public class Tank {
    //方向
    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;
    public static final int DIR_LEFT = 2;
    public static final int DIR_RIGHT = 3;

    //状态
    public static final int STATE_STANDING = 0;
    public static final int STATE_MOVING = 1;
    public static final int STATE_DEAD = 2;

    //半径25像素
    private static final int RADIUS = 25;

    //默认速度 每帧4像素，目前的刷新率是每秒33帧
    static final int DEFAULT_SPEED = 4;

    private int state = STATE_STANDING;
    //坐标
    private int x,y;

    //血量
    private int hp = 1000;

    //攻击力
    private int atk;

    //速度
    private int speed = DEFAULT_SPEED;

    //当前的方向
    private DirectionEnum dir;

    //坦克颜色
    private Color color;

    private boolean trigger = false;

    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
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
        this.color = Utils.getRandomColor();
    }


    /**
     * 绘制坦克
     * @param g 画笔
     */
    public void draw(Graphics g) {
        g.setColor(this.color);
        if (this.state == STATE_STANDING) {
            g.fillOval(x - RADIUS,y - RADIUS,RADIUS * 2,RADIUS * 2);
            int endX,endY;
            if (dir == DirectionEnum.UP) {
                endX = x;
                endY = y - 2 * RADIUS;
                g.drawLine(x,y,endX,endY);
                g.drawLine(x - 1,y,endX - 1,endY);
                g.drawLine(x + 1,y,endX + 1,endY);
            } else if (dir == DirectionEnum.DOWN) {
                endX = x;
                endY = y + 2 * RADIUS;
                g.drawLine(x,y,endX,endY);
                g.drawLine(x - 1,y,endX - 1,endY);
                g.drawLine(x + 1,y,endX + 1,endY);
            } else if (dir == DirectionEnum.RIGHT) {
                endX = x + 2 * RADIUS;
                endY = y;
                g.drawLine(x,y,endX,endY);
                g.drawLine(x,y + 1,endX,endY + 1);
                g.drawLine(x,y - 1,endX,endY - 1);
            } else if (dir == DirectionEnum.LEFT) {
                endX = x - 2 * RADIUS;
                endY = y;
                g.drawLine(x,y,endX,endY);
                g.drawLine(x,y + 1,endX,endY + 1);
                g.drawLine(x,y - 1,endX,endY - 1);
            }
        } else if (this.state == STATE_MOVING) {
            int endX,endY;
            if (dir == DirectionEnum.UP) {
                y = y - speed;
                if (y < RADIUS + MainFrame.titleHight) {
                    y = RADIUS + MainFrame.titleHight;
                }
                endX = x;
                endY = y - 2 * RADIUS;
                g.fillOval(x - RADIUS,y - RADIUS,RADIUS * 2,RADIUS * 2);
                g.drawLine(x,y,endX,endY);
                g.drawLine(x - 1,y,endX - 1,endY);
                g.drawLine(x + 1,y,endX + 1,endY);
            } else if (dir == DirectionEnum.DOWN) {
                y = y + speed;
                if (y > TankConstants.FRAME_HIGHT - RADIUS) {
                    y = TankConstants.FRAME_HIGHT - RADIUS;
                }
                System.out.println("y = " + y);
                endX = x;
                endY = y + 2 * RADIUS;
                g.fillOval(x - RADIUS,y - RADIUS,RADIUS * 2,RADIUS * 2);
                g.drawLine(x,y,endX,endY);
                g.drawLine(x - 1,y,endX - 1,endY);
                g.drawLine(x + 1,y,endX + 1,endY);
            } else if (dir == DirectionEnum.RIGHT) {
                x = x + speed;
                if (x > TankConstants.FRAME_WIDTH - RADIUS) {
                    x = TankConstants.FRAME_WIDTH - RADIUS;
                }
                endX = x + 2 * RADIUS;
                endY = y;
                g.fillOval(x - RADIUS,y - RADIUS,RADIUS * 2,RADIUS * 2);
                g.drawLine(x,y,endX,endY);
                g.drawLine(x,y + 1,endX,endY + 1);
                g.drawLine(x,y - 1,endX,endY - 1);
            } else if (dir == DirectionEnum.LEFT) {
                x = x - speed;
                if (x < RADIUS) {
                    x = RADIUS;
                }
                endX = x - 2 * RADIUS;
                endY = y;
                g.fillOval(x - RADIUS,y - RADIUS,RADIUS * 2,RADIUS * 2);

                g.drawLine(x,y,endX,endY);
                g.drawLine(x,y + 1,endX,endY + 1);
                g.drawLine(x,y - 1,endX,endY - 1);
            }
        }

        for (Bullet bullet : bullets) {
            bullet.fly(g,dir);
        }

    }

    public void launch(Graphics g) {
        Bullet bullet = null;
        if (DirectionEnum.UP == dir) {
            bullet = new StandardBullet(x,y - 2 * RADIUS,dir);
        } else if (DirectionEnum.DOWN == dir) {
            bullet = new StandardBullet(x,y + 2 * RADIUS,dir);
        } else if (DirectionEnum.LEFT == dir) {
            bullet = new StandardBullet(x - 2 * RADIUS,y,dir);
        } else if (DirectionEnum.RIGHT == dir) {
            bullet = new StandardBullet(x + 2 * RADIUS,y,dir);
        }
        bullets.add(bullet);
    }

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
}
