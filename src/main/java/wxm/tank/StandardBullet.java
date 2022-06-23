package wxm.tank;

import wxm.tank.constant.DirectionEnum;
import wxm.tank.constant.TankConstants;
import wxm.tank.util.BulletPool;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenxiangmin
 * @ClassName StandardBullet.java
 * @Description 标准子弹类
 * @createTime 2022年06月13日 21:36:00
 */
public class StandardBullet extends Bullet{
    private int WIDTH = 20;
    private int HEIGHT = 30;
    //子弹图片
    public static java.util.Map<DirectionEnum, Image> bulletMap;

    static {
        //初始化坦克图片
        bulletMap = new HashMap<>(4);
        InputStream upIs = Tank.class.getClassLoader().getResourceAsStream("img/bullet_u.gif");
        InputStream downIs = Tank.class.getClassLoader().getResourceAsStream("img/shot_bottom.gif");
        InputStream leftIs = Tank.class.getClassLoader().getResourceAsStream("img/shot_left.gif");
        InputStream rightIs = Tank.class.getClassLoader().getResourceAsStream("img/shot_right.gif");
        byte[] imgData = new byte[6048];
        try {
            upIs.read(imgData);
            Image imaUp = new ImageIcon(imgData).getImage();
            bulletMap.put(DirectionEnum.UP,imaUp);
            downIs.read(imgData);
            Image imaDown = new ImageIcon(imgData).getImage();
            bulletMap.put(DirectionEnum.DOWN,imaDown);
            leftIs.read(imgData);
            Image imaLeft = new ImageIcon(imgData).getImage();
            bulletMap.put(DirectionEnum.LEFT,imaLeft);
            rightIs.read(imgData);
            Image imaRight = new ImageIcon(imgData).getImage();
            bulletMap.put(DirectionEnum.RIGHT,imaRight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StandardBullet() {
        super.speed = Tank.DEFAULT_SPEED * 2;
    }

    public StandardBullet(int x, int y, DirectionEnum dir) {
        super.dir = dir;
        super.x = x;
        super.y = y;
        super.speed = Tank.DEFAULT_SPEED * 2;
    }

    @Override
    public void fly(Graphics g, DirectionEnum tankDir) {
        g.setColor(color);
        if (DirectionEnum.UP == dir) {
            y = y - speed;
            if (y < 0) {
                setVisible(false);
                return;
            }
            g.drawImage(bulletMap.get(dir),x- (WIDTH / 2),y - (HEIGHT / 2) - (Tank.HIGHT / 2),WIDTH,HEIGHT,null);
        } else if (DirectionEnum.DOWN == dir) {
            y = y + speed;
            if (y > TankConstants.FRAME_HIGHT) {
                setVisible(false);
                return;
            }

            g.drawImage(bulletMap.get(dir),x- (WIDTH / 2),y - (HEIGHT / 2) + (Tank.HIGHT / 2),WIDTH,HEIGHT,null);
        } else if (DirectionEnum.LEFT == dir) {
            x = x - speed;
            if (x < 0) {
                setVisible(false);
                return;
            }

            g.drawImage(bulletMap.get(dir),x- (HEIGHT / 2) - (Tank.HIGHT / 2),y - (WIDTH / 2),HEIGHT,WIDTH,null);
        } else if (DirectionEnum.RIGHT == dir) {
            x = x + speed;
            if (x > TankConstants.FRAME_WIDTH) {
                setVisible(false);
                return;
            }
            g.drawImage(bulletMap.get(dir),x- (HEIGHT / 2) + (Tank.HIGHT / 2),y - (WIDTH / 2),HEIGHT,WIDTH,null);
        }
    }
}
