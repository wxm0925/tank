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
public class Tank {
    //坦克图片的宽高
    public static final int WIDTH = TankConstants.TANK_WIDTH;
    public static final int HIGHT = TankConstants.TANK_HEIGHT;
    //坦克图片
    public static Map<DirectionEnum,Image> imgMap;


    static {
        //初始化坦克图片
        imgMap = new HashMap<>(4);
        InputStream upIs = Tank.class.getClassLoader().getResourceAsStream("img/tank_u.gif");
        InputStream downIs = Tank.class.getClassLoader().getResourceAsStream("img/tank_d.gif");
        InputStream leftIs = Tank.class.getClassLoader().getResourceAsStream("img/tank_l.gif");
        InputStream rightIs = Tank.class.getClassLoader().getResourceAsStream("img/tank_r.gif");
        byte[] imgData = new byte[6048];
        try {
            upIs.read(imgData);
            Image imaUp = new ImageIcon(imgData).getImage();
            imgMap.put(DirectionEnum.UP,imaUp);
            downIs.read(imgData);
            Image imaDown = new ImageIcon(imgData).getImage();
            imgMap.put(DirectionEnum.DOWN,imaDown);
            leftIs.read(imgData);
            Image imaLeft = new ImageIcon(imgData).getImage();
            imgMap.put(DirectionEnum.LEFT,imaLeft);
            rightIs.read(imgData);
            Image imaRight = new ImageIcon(imgData).getImage();
            imgMap.put(DirectionEnum.RIGHT,imaRight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //状态
    public static final int STATE_STANDING = 0;
    public static final int STATE_MOVING = 1;
    public static final int STATE_DEAD = 2;

    //默认速度 每帧4像素，目前的刷新率是每秒33帧
    static final int DEFAULT_SPEED = 5 ;

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
            if (dir == DirectionEnum.UP) {
                g.drawImage(imgMap.get(DirectionEnum.UP),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.DOWN) {
                g.drawImage(imgMap.get(DirectionEnum.DOWN),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.RIGHT) {
                g.drawImage(imgMap.get(DirectionEnum.RIGHT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            } else if (dir == DirectionEnum.LEFT) {
                g.drawImage(imgMap.get(DirectionEnum.LEFT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            }
        } else if (this.state == STATE_MOVING) {
            if (dir == DirectionEnum.UP) {
                y = y - speed;
                if (y < MainFrame.titleHight + (HIGHT / 2)) {
                    y = MainFrame.titleHight + (HIGHT / 2);
                }
                g.drawImage(imgMap.get(DirectionEnum.UP),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.DOWN) {
                y = y + speed;
                if (y > TankConstants.FRAME_HIGHT - (HIGHT / 2)) {
                    y = TankConstants.FRAME_HIGHT - (HIGHT / 2);
                }
                g.drawImage(imgMap.get(DirectionEnum.DOWN),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.RIGHT) {
                x = x + speed;
                if (x > TankConstants.FRAME_WIDTH - (HIGHT / 2)) {
                    x = TankConstants.FRAME_WIDTH - (HIGHT / 2);
                }
                g.drawImage(imgMap.get(DirectionEnum.RIGHT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            } else if (dir == DirectionEnum.LEFT) {
                x = x - speed;
                if (x < HIGHT / 2) {
                    x = HIGHT / 2;
                }
                g.drawImage(imgMap.get(DirectionEnum.LEFT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            }
        }

        for (Bullet bullet : bullets) {
            if (bullet.isVisible()) {
                bullet.fly(g,dir);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (!bullet.isVisible()) {
                Bullet remove = bullets.remove(i);
                BulletPool.release(remove);
            }
        }

        /*
        NOTE：从集合中删除元素，Java8写法，如果是java8以前，就用迭代器删除
         */
        bullets.removeIf(bullet -> !bullet.isVisible());

    }

    public void launch(Graphics g) {
        Bullet bullet = BulletPool.get();
        bullet.setX(x);
        bullet.setY(y);
        bullet.setDir(dir);
        bullet.setVisible(true);
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
