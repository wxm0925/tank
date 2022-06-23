package wxm.tank;

import wxm.tank.constant.DirectionEnum;
import wxm.tank.constant.TankConstants;
import wxm.tank.frame.MainFrame;
import wxm.tank.util.BulletPool;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenxiangmin
 * @ClassName PlayerTank.java
 * @Description 玩家坦克
 * @createTime 2022年06月23日 16:59:00
 */
public class PlayerTank extends Tank{
    //坦克图片
    public static Map<DirectionEnum, Image> imgMap;

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


    public PlayerTank(int x, int y,DirectionEnum dir) {
        super(x, y, dir);
    }

    @Override
    public void draw(Graphics g) {
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
}
